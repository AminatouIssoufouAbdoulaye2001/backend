package Pfe.SpringBoot.BackEnd.repositories;

import Pfe.SpringBoot.BackEnd.entities.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  void deleteUserById(Long id);
}
