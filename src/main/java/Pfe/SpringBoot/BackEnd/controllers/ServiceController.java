package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.dtos.GetServiceDTO;
import Pfe.SpringBoot.BackEnd.dtos.NGHostResponseDTO;
import Pfe.SpringBoot.BackEnd.dtos.PostServiceDTO;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost400Exception;
import Pfe.SpringBoot.BackEnd.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
