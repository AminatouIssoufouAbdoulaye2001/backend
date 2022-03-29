package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.services.TicketService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/tickets")
public class TicketController {
private static TicketService ticketService;
public TicketController(TicketService ticketService){
  this.ticketService=ticketService;
}
}
