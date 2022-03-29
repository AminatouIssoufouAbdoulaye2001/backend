package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.services.ServiceService;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/services")
public class ServiceController {
private static ServiceService serviceService;
public ServiceController (ServiceService serviceService){
  this.serviceService=serviceService;
}
}
