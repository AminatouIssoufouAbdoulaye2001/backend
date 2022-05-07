package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.dtos.*;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost400Exception;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost401Exception;
import Pfe.SpringBoot.BackEnd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<NGHostResponseDTO> createAccount(
            @RequestBody() UserAccountDTO createUserDTO) throws NGHost400Exception {
        return ResponseEntity.ok(userService.create(createUserDTO));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<NGHostResponseDTO> login(
            @RequestBody() LoginDTO loginDTO) throws NGHost401Exception {

        return ResponseEntity.ok(userService.login(loginDTO));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/{id}/profil")
    public ResponseEntity<NGHostResponseDTO> getProfil(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("id") long userId
    ) throws NGHost400Exception, NGHost401Exception {

        userService.checkUserIdentity(token, userId);
        return ResponseEntity.ok(userService.getProfil(userId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PatchMapping(value = "/{id}/profil")
    public ResponseEntity<NGHostResponseDTO> patchProfil(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("id") long userId,
            @RequestBody UserProfilDTO userProfilDTO
    ) throws NGHost400Exception, NGHost401Exception {

        userService.checkUserIdentity(token, userId);
        return ResponseEntity.ok(userService.patchProfil(userProfilDTO)
        );
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PatchMapping(value = "/{id}/password")
    public ResponseEntity<NGHostResponseDTO> patchPassword(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("id") long userId,
            @RequestBody PatchPasswordDTO passwordDTO
    ) throws NGHost400Exception, NGHost401Exception {

        userService.checkUserIdentity(token, userId);
        return ResponseEntity.ok(userService.patchPassword(passwordDTO));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PatchMapping("/{id}/is-active")
    public ResponseEntity<NGHostResponseDTO> desableUserAccount(
            @PathVariable("id") long userId
    ) throws NGHost400Exception {

        return ResponseEntity.ok(userService.desableAccount(userId));
    }
}
