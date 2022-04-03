package Pfe.SpringBoot.BackEnd.dtos;

import lombok.Data;

@Data
public class LoginDTO {
    private String userName;
    private String email;
    private String password;
}
