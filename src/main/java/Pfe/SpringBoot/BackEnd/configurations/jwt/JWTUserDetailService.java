package Pfe.SpringBoot.BackEnd.configurations.jwt;

import Pfe.SpringBoot.BackEnd.constantes.ERole;
import Pfe.SpringBoot.BackEnd.dtos.UserAccountDTO;
import Pfe.SpringBoot.BackEnd.entities.User;
import Pfe.SpringBoot.BackEnd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class JWTUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);
        if (!userOptional.isPresent())
            throw new UsernameNotFoundException("Le nom d'utilisateur n'existe pas.");

        if (!userOptional.get().isActive())
            throw new UsernameNotFoundException("Votre compte est bloqué Contactez l'administation du site.");

        List<GrantedAuthority> roles = Arrays.asList(
                new SimpleGrantedAuthority(userOptional.get().getRole().name())
        );

        return new org.springframework.security.core.userdetails.User(userOptional.get().getUserName(),
                userOptional.get().getPassword(), roles);
    }

    public User save(UserAccountDTO userAccountDTO) {
        User user = UserAccountDTO.dtoToModel(userAccountDTO);
        user.setRole(ERole.ROLE_CLIENT);
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        user.setActive(true);
        return userRepository.save(user);
    }

    public Collection<ERole> getRolesByToken(String token) {
        String payload = jwtUtil.getRolesFromToken(token);
        Collection<ERole> roles = new ArrayList<>();
        if (!payload.isEmpty())
            for (String role : payload.split(","))
                roles.add(ERole.valueOf(role));

        return roles;
    }

    public String EncodePassword(String password) {
        return bcryptEncoder.encode(password);
    }
}
