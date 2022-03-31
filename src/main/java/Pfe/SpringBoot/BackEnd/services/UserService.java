package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.dtos.APIResponseDTO;
import Pfe.SpringBoot.BackEnd.dtos.UserAccountDTO;
import Pfe.SpringBoot.BackEnd.dtos.LoginDTO;
import Pfe.SpringBoot.BackEnd.entities.User;
import Pfe.SpringBoot.BackEnd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private Validator validator;

    private static UserRepository userRepository;

    @Autowired
    public UserService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public APIResponseDTO create(UserAccountDTO userAccountDTO) {
        Set<ConstraintViolation<UserAccountDTO>> violations = validator.validate(userAccountDTO);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();

            for(ConstraintViolation<UserAccountDTO> constraintViolation: violations) {
                sb.append(constraintViolation.getMessage());
            }
        }

        User newUser = new User();
        newUser.setFirstName(userAccountDTO.getFirstName());
        newUser.setLastName(userAccountDTO.getLastName());
        newUser.setEmail(userAccountDTO.getEmail());
        newUser.setPhone(userAccountDTO.getPhone());
        newUser.setPassword(userAccountDTO.getPassword());

        userRepository.save(newUser);
        return new APIResponseDTO("Notre compte a été crée");
    }

    public APIResponseDTO login(LoginDTO loginDTO) {
        // code métier

        // retourner le token
        return new APIResponseDTO("token :)");
    }

    public Collection<User> findAllUser() {
        return (Collection<User>) userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }
}
