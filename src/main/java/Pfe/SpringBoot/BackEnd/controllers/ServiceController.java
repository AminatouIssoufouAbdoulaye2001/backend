package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.dtos.NGHostResponseDTO;
import Pfe.SpringBoot.BackEnd.services.ServiceService;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/v1/services")
public class ServiceController {

    @Autowired
    private static ServiceService serviceService;

    public ResponseEntity<NGHostResponseDTO> postService() {

        return ResponseEntity.ok(new NGHostResponseDTO(null));
    }

    public ResponseEntity<NGHostResponseDTO> patchService() {

        return ResponseEntity.ok(new NGHostResponseDTO(null));
    }

    public ResponseEntity<NGHostResponseDTO> getServices() {

        return ResponseEntity.ok(new NGHostResponseDTO(null));
    }

    public ResponseEntity<NGHostResponseDTO> delete() {

        return ResponseEntity.ok(new NGHostResponseDTO(null));
    }
}
