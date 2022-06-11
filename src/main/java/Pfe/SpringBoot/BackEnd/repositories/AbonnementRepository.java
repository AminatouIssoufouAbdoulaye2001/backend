package Pfe.SpringBoot.BackEnd.repositories;
import Pfe.SpringBoot.BackEnd.entities.Abonnement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface AbonnementRepository extends CrudRepository<Abonnement, Long> {
    List<Abonnement> findByCustomerEmail(String email);
    List<Abonnement> findAll();

}
