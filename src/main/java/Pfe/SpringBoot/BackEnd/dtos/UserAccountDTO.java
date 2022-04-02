package Pfe.SpringBoot.BackEnd.dtos;

import Pfe.SpringBoot.BackEnd.entities.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserAccountDTO {
    @NotBlank(message = "le nom est obligatoire")
    private String firstName;

    @NotBlank(message = "le prénom est obligatoire")
    private String lastName;

    @NotBlank(message = "l'adresse email est obligatoire")
    @Email(message = "le format de l'adresse n'est pas conforme")
    private String email;

    @NotBlank(message = "le mot de passe est obligatoire")
    private String password;

    @NotBlank(message = "la confirmation du mot de passe est obligatoire est obligatoire")
    private String confirmePassword;

    public static User dtoToModel(UserAccountDTO dto) {
       User user = new User();
       user.setFirstName(dto.getFirstName());
       user.setLastName(dto.getFirstName());
       user.setEmail(dto.getEmail());
       user.setPassword(dto.getPassword());

       return user;
    }
}
