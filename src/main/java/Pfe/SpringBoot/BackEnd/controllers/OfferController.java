package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.services.OfferService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/offers")
public class OfferController {
private static OfferService offerService;
public OfferController(OfferService offerService) {
  this.offerService=offerService;
}
}
