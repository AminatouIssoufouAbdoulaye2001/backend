package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.dtos.GetServiceDTO;
import Pfe.SpringBoot.BackEnd.dtos.Godaddy;
import Pfe.SpringBoot.BackEnd.dtos.NGHostResponseDTO;
import Pfe.SpringBoot.BackEnd.dtos.PostServiceDTO;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost400Exception;
import Pfe.SpringBoot.BackEnd.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/services")
public class ServiceController {

    @Autowired
    ServiceService productService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<NGHostResponseDTO> postService(@RequestBody PostServiceDTO dto) {
        return ResponseEntity.ok(
                productService.create(dto)
        );
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(value = "/{id}")
    public ResponseEntity<NGHostResponseDTO> patchService(
            @PathVariable("id") long serviceId,
            @RequestBody GetServiceDTO dto
    ) throws NGHost400Exception {

        return ResponseEntity.ok(
                productService.update(serviceId, dto)
        );
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<NGHostResponseDTO> getOne(@PathVariable("id") long id)
            throws NGHost400Exception {
        return ResponseEntity.ok(
                productService.getOne(id)
        );
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<NGHostResponseDTO> delete(@PathVariable("id") long serviceId)
            throws NGHost400Exception {

        return ResponseEntity.ok(
                this.productService.delete(serviceId)
        );
    }

    @GetMapping
    public ResponseEntity<NGHostResponseDTO> getServices() {
        return ResponseEntity.ok(
                productService.getAll()
        );
    }



    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
    @GetMapping("/subscribe")
    public ResponseEntity<NGHostResponseDTO> getSubscribedServices(
            @RequestHeader(value = "Authorization") String token
    ) throws NGHost400Exception {
        return ResponseEntity.ok(productService.getCustomerSubscribedProduct(
                token
        ));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
    @PostMapping("/subscribe")
    public ResponseEntity<NGHostResponseDTO> subscribeToServices(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody List<Long> servicesIds ) throws NGHost400Exception {
        return ResponseEntity.ok(productService.subscribeToService( token, servicesIds )); }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/subscriptions")
    public ResponseEntity<NGHostResponseDTO> getSubscriptions() {
        return ResponseEntity.ok(
                productService.getSubscriptions()
        );
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
    @PostMapping("/save-domain")
    public ResponseEntity<NGHostResponseDTO> SaveDomain(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Godaddy godaddy
    ) throws NGHost400Exception {
        return ResponseEntity.ok(productService.SaveDomain(
                godaddy,
                token
        ));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
    @GetMapping("/domaines")
    public ResponseEntity<NGHostResponseDTO> GetAllDomain(

    ) throws NGHost400Exception {
        return ResponseEntity.ok(productService.getAllDomain());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
    @GetMapping("/client-domain")
    public ResponseEntity<NGHostResponseDTO> getClientDomain(
            @RequestHeader(value = "Authorization") String token
    ) throws NGHost400Exception {
        return ResponseEntity.ok(productService.ClientDomain(
                token
        ));
    }
}
