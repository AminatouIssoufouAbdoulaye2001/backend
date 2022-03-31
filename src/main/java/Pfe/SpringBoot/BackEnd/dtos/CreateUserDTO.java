package Pfe.SpringBoot.BackEnd.dtos;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String confirmePassword;
}
