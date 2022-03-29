package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.services.PaymentService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/payment")
public class PaymentController {
private static PaymentService paymentService;
public PaymentController(PaymentService paymentService){
  this.paymentService=paymentService;
}
}
