package Pfe.SpringBoot.BackEnd.repositories;

import Pfe.SpringBoot.BackEnd.entities.Domaine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DomainRepository extends CrudRepository<Domaine, Long> {
        List<Domaine> findAllByCustomer(String customer);

}
