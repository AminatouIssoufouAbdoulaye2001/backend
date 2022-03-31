package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.dtos.APIResponseDTO;
import Pfe.SpringBoot.BackEnd.dtos.CreateUserDTO;
import Pfe.SpringBoot.BackEnd.dtos.LoginDTO;
import Pfe.SpringBoot.BackEnd.entities.User;
import Pfe.SpringBoot.BackEnd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    private static UserRepository userRepository;

    @Autowired
    public UserService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public APIResponseDTO create(CreateUserDTO createUserDTO) {
        // validate inputs + email unique

        User newUser = new User();
        newUser.setFirstName(createUserDTO.getFirstName());
        newUser.setLastName(createUserDTO.getLastName());
        newUser.setEmail(createUserDTO.getEmail());
        newUser.setPhone(createUserDTO.getPhone());
        newUser.setPassword(createUserDTO.getPassword());

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
