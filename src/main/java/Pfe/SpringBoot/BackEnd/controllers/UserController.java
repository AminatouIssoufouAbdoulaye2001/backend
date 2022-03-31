package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.dtos.APIResponseDTO;
import Pfe.SpringBoot.BackEnd.dtos.CreateUserDTO;
import Pfe.SpringBoot.BackEnd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<APIResponseDTO> createAccount(@RequestBody() CreateUserDTO createUserDTO) {
        return ResponseEntity.ok(userService.create(createUserDTO));
    }
}
