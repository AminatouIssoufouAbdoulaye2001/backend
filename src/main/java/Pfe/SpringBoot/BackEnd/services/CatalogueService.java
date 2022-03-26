package Pfe.SpringBoot.BackEnd.services;

import Pfe.SpringBoot.BackEnd.repositories.CatalogueRepository;
import org.springframework.stereotype.Service;

@Service
public class CatalogueService {
  private static CatalogueRepository catalogueRepository;

public CatalogueService(CatalogueRepository catalogueRepository){
  this.catalogueRepository=catalogueRepository;
}
}
