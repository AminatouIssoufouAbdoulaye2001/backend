package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.services.CatalogueService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/catalogues")
public class CatalogueController {
private static CatalogueService catalogueService;
public CatalogueController(CatalogueService catalogueService){
  this.catalogueService=catalogueService;
}
}
