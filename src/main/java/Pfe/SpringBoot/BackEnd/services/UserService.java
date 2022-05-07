package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.configurations.jwt.JWTUserDetailService;
import Pfe.SpringBoot.BackEnd.configurations.jwt.JWTUtil;
import Pfe.SpringBoot.BackEnd.dtos.*;
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

    private static final String TOKEN_PREFIX = "Bearer ";

    private final String UNAUTHORIZED_MESSAGE = "Utilisateur non authentifié";

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

    public NGHostResponseDTO getProfil(final long userId) throws NGHost400Exception {

        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new NGHost400Exception("L' utilisateur introuvable");
        }

        UserProfilDTO userProfil = new UserProfilDTO(user.get());
        return new NGHostResponseDTO(userProfil);
    }

    public NGHostResponseDTO patchProfil(UserProfilDTO userProfilDTO)
            throws NGHost400Exception {

        Set<ConstraintViolation<UserProfilDTO>> violations = validator.validate(userProfilDTO);
        if (!violations.isEmpty()) {
            // retourner la première contrainte non vérifiéeu
            ConstraintViolation<UserProfilDTO> constraintViolation =
                    violations.stream().findFirst().get();
            throw new NGHost400Exception(constraintViolation.getMessage());
        }

        Optional<User> optUser = userRepository.findById(userProfilDTO.getId());

        if (!optUser.get().getUserName().equals(userProfilDTO.getUsername())) {
            Optional<User> optionalUser = userRepository.findByUserName(userProfilDTO.getUsername());
            if (optionalUser.isPresent()) {
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

    public NGHostResponseDTO patchPassword(final PatchPasswordDTO passwordDTO)
            throws NGHost400Exception, NGHost401Exception {
        checkUserIdentity(passwordDTO.getUserId());

        User user = userRepository.findById(passwordDTO.getUserId()).get();

        if (!passwordDTO.getNewPassword().equals(passwordDTO.getConfNewPassword())) {
            throw new NGHost400Exception(
                    "Le mot de passe de confirmation est différent du nouveau"
            );
        }

        user.setPassword(
                jwtUserDetailService.EncodePassword(passwordDTO.getNewPassword())
        );

        userRepository.save(user);

        return new NGHostResponseDTO("Le mot de passe a été modifié");
    }

    public NGHostResponseDTO desableAccount(long userId) throws NGHost400Exception{
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new NGHost400Exception("Utilisateur non identifié");
        }

        User user = optionalUser.get();
        user.setActive(false);
        userRepository.save(user);

        return  new NGHostResponseDTO("Le compte a été désactivé");
    }

    public void checkUserIdentity(String token, long userId) throws NGHost401Exception {
        String username = jwtUtil.getUsernameFromToken(
                token.replace(TOKEN_PREFIX, "")
        );

        Optional<User> optionalUser = userRepository.findByUserName(username);
        if (!optionalUser.isPresent() || optionalUser.get().getId() != userId) {
            throw new NGHost401Exception(UNAUTHORIZED_MESSAGE);
        }
    }

    private void checkUserIdentity(long userId) throws NGHost401Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new NGHost401Exception(UNAUTHORIZED_MESSAGE);
        }
    }
}
