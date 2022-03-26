package Pfe.SpringBoot.BackEnd.repositories;

import Pfe.SpringBoot.BackEnd.entities.Catalogue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CatalogueRepository extends CrudRepository<Catalogue,Long> {


}
