package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.configurations.jwt.JWTUserDetailService;
import Pfe.SpringBoot.BackEnd.configurations.jwt.JWTUtil;
import Pfe.SpringBoot.BackEnd.dtos.LoginDTO;
import Pfe.SpringBoot.BackEnd.dtos.NGHostResponseDTO;
import Pfe.SpringBoot.BackEnd.dtos.UserAccountDTO;
import Pfe.SpringBoot.BackEnd.dtos.UserProfilDTO;
import Pfe.SpringBoot.BackEnd.entities.User;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost400Exception;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost401Exception;
import Pfe.SpringBoot.BackEnd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private Validator validator;

    @Autowired
    private JWTUserDetailService jwtUserDetailService;

    @Autowired
    private JWTUtil jwtUtil;

    private static UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public NGHostResponseDTO create(UserAccountDTO userAccountDTO) throws NGHost400Exception {
        Set<ConstraintViolation<UserAccountDTO>> violations = validator.validate(userAccountDTO);
        if (!violations.isEmpty()) {
            // retourner la première contrainte non vérifiée
            ConstraintViolation<UserAccountDTO> constraintViolation =
                    violations.stream().findFirst().get();
            throw new NGHost400Exception(constraintViolation.getMessage());
        }

        Optional<User> existingUserWithProvidedEmail = userRepository.findByEmail(userAccountDTO.getEmail());
        if (existingUserWithProvidedEmail.isPresent()) {
            throw new NGHost400Exception(" l'adresse email existe déjà");
        }

        Optional<User> existingUserWithProvidedUsername = userRepository.findByUserName(userAccountDTO.getUserName());
        if (existingUserWithProvidedUsername.isPresent()) {
            throw new NGHost400Exception("Le nom d'utilisateur existe déjà");
        }

        if (!userAccountDTO.getPassword().equals(userAccountDTO.getPasswordConf())) {
            throw new NGHost400Exception("le mot de passe diffère du mot de passe de confirmation");
        }

        jwtUserDetailService.save(userAccountDTO);
        NGHostResponseDTO response = new NGHostResponseDTO(null);
        response.setMessage("Compte crée avec succès");
        return response;
    }

    public NGHostResponseDTO login(LoginDTO loginDTO) throws NGHost401Exception {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword());
        try {
            authManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            throw new NGHost401Exception("Le username ou le mot de passe est invalid");
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(loginDTO.getUserName());

        return new NGHostResponseDTO(jwtUtil.generateToken(userDetails));
    }

    public NGHostResponseDTO getProfil (final  String username) throws NGHost400Exception{
        if (username.isEmpty()) {
            throw new NGHost400Exception("Le username est invalid");
        }

        Optional<User> user = userRepository.findByUserName(username);
        if (!user.isPresent()) {
            throw new NGHost400Exception("L' utilisateur introuvale");
        }
        UserProfilDTO userProfil = new UserProfilDTO(user.get());
        return new NGHostResponseDTO(userProfil);
    }

    public NGHostResponseDTO patchProfil (
            final  String username,
            final long userId,
            UserProfilDTO userProfilDTO
    ) throws NGHost400Exception{

        Set<ConstraintViolation<UserProfilDTO>> violations = validator.validate(userProfilDTO);
        if (!violations.isEmpty()) {
            // retourner la première contrainte non vérifiéeu
            ConstraintViolation<UserProfilDTO> constraintViolation =
                    violations.stream().findFirst().get();
            throw new NGHost400Exception(constraintViolation.getMessage());
        }

        Optional<User> optUser = userRepository.findById(userId);
        if (!optUser.isPresent() || !optUser.get().getUserName().equals(username)) {
            throw new NGHost400Exception("L' utilisateur introuvale");
        }

        if (!optUser.get().getUserName().equals(userProfilDTO.getUsername())) {
            Optional<User> optionalUser = userRepository.findByUserName(userProfilDTO.getUsername());
            if(optionalUser.isPresent()) {
                throw new NGHost400Exception("Le nom d'utilisateur est déjà utilisé");
            }
            optUser.get().setUserName(userProfilDTO.getUsername());
        }

        User user = optUser.get();
        user.setFullName(userProfilDTO.getFullName());
        user.setEmail(userProfilDTO.getEmail());
        user.setPhone(userProfilDTO.getPhone());

        userRepository.save(user);
        return new NGHostResponseDTO(userProfilDTO);
    }
}
