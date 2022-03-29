package Pfe.SpringBoot.BackEnd.controllers;

import Pfe.SpringBoot.BackEnd.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/users")
public class UserController {
private static UserService userService;
public UserController(UserService userService){
  this.userService=userService;
}

}
