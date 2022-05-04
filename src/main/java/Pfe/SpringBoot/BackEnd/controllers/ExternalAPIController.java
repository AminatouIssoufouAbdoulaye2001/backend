package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.dtos.NGHostResponseDTO;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost400Exception;
import Pfe.SpringBoot.BackEnd.services.ExternalAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/external-api/")
public class ExternalAPIController {
    @Autowired
    ExternalAPIService externalAPIService;

    @GetMapping(value = "godaddy/domains/available")
    public ResponseEntity<NGHostResponseDTO> getDomainAvailability(@RequestParam() String domain)
            throws NGHost400Exception {
        return ResponseEntity.ok(
                externalAPIService.getDomainAvailability(domain)
        );
    }

    @PostMapping(value = "godaddy/domains/available")
    public ResponseEntity<NGHostResponseDTO> getDomainsAvailabilities(@RequestBody List<String> domains)
            throws NGHost400Exception {
        return ResponseEntity.ok(
                externalAPIService.getDomainsAvailabilities(domains)
        );
    }
}
