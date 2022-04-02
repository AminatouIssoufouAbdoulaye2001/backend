package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.dtos.NGHostResponseDTO;
import Pfe.SpringBoot.BackEnd.dtos.UserAccountDTO;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost400Exception;
import Pfe.SpringBoot.BackEnd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<NGHostResponseDTO> createAccount(@RequestBody() UserAccountDTO createUserDTO)  throws NGHost400Exception{
        return ResponseEntity.ok(userService.create(createUserDTO));
    }
}
