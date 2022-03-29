package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.repositories.FactureRepository;
import org.springframework.stereotype.Service;

@Service
public class FactureService {

  private final FactureRepository factureRepository;
public FactureService(FactureRepository factureRepository){
  this.factureRepository=factureRepository;
}

}
