package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.configurations.jwt.JWTUtil;
import Pfe.SpringBoot.BackEnd.dtos.LoginDTO;
import Pfe.SpringBoot.BackEnd.dtos.NGHostResponseDTO;
import Pfe.SpringBoot.BackEnd.dtos.UserAccountDTO;
import Pfe.SpringBoot.BackEnd.dtos.UserProfilDTO;
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
    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping()
    public ResponseEntity<NGHostResponseDTO> createAccount(@RequestBody() UserAccountDTO createUserDTO) throws NGHost400Exception {
        return ResponseEntity.ok(userService.create(createUserDTO));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<NGHostResponseDTO> login(@RequestBody() LoginDTO loginDTO) throws NGHost401Exception {
        return ResponseEntity.ok(userService.login(loginDTO));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/profil")
    public ResponseEntity<NGHostResponseDTO> getProfil(
            @RequestHeader(value = "Authorization") String token
    ) throws NGHost400Exception {
        String username = jwtUtil.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));
        return ResponseEntity.ok(userService.getProfil(username));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PatchMapping(value = "/{id}/profil")
    public ResponseEntity<NGHostResponseDTO> patchProfil(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("id") long userId,
            @RequestBody UserProfilDTO userProfilDTO
    ) throws NGHost400Exception {
        String username = jwtUtil.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));
        return ResponseEntity.ok(userService.patchProfil(
                        username,
                        userId,
                        userProfilDTO
                )
        );
    }
}
