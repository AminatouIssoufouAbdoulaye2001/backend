package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.services.FactureService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/factures")
public class FactureController {
  private static FactureService factureService;
  public FactureController(FactureService factureService){
    this.factureService=factureService;
  }

}
