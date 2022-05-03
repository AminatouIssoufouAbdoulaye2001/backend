package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.dtos.NGHostResponseDTO;
import Pfe.SpringBoot.BackEnd.services.ExternalAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/external-api/")
public class ExternalAPIController {
    @Autowired
    ExternalAPIService externalAPIService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "godaddy/domains/avaible")
    public ResponseEntity<NGHostResponseDTO> getDomainAvailablity(@RequestParam() String domain) {
        return ResponseEntity.ok(externalAPIService.getDomainAvailablity(domain));
    }
}
