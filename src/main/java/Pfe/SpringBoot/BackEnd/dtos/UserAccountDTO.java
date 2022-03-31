package Pfe.SpringBoot.BackEnd.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserAccountDTO {
    @NotBlank(message = "le nom est obligatoire")
    private String firstName;

    @NotBlank(message = "le prénom est obligatoire")
    private String lastName;

    @NotBlank(message = "l'adresse email est obligatoire")
    private String email;

    private String phone;

    @NotBlank(message = "le mot de passe est obligatoire")
    private String password;

    @NotBlank(message = "la confirmation du mot de passe est obligatoire est obligatoire")
    private String confirmePassword;
}
