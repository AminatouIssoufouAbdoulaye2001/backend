package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.entities.User;
import Pfe.SpringBoot.BackEnd.repositories.UserRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

  private static UserRepository userRepository;

  public User addUser(User user) {
    user.setUserCode(UUID.randomUUID().toString());
    return userRepository.save(user);
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
