package Pfe.SpringBoot.BackEnd.dtos;

import Pfe.SpringBoot.BackEnd.entities.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserAccountDTO {

    private long idPlesk;

    @NotBlank(message = "le username est obligatoire")
    private String userName;

    @NotBlank(message = "le nom & et le prénom sont obligatoires")
    private String fullName;

    @NotBlank(message = "l'adresse email est obligatoire")
    @Email(message = "le format de l'adresse n'est pas valid")
    private String email;

    @NotBlank(message = "le mot de passe est obligatoire")
    private String password;

    @NotBlank(message = "la confirmation du mot de passe est obligatoire est obligatoire")
    private String passwordConf;

    public static User dtoToModel(UserAccountDTO dto) {
       User user = new User();
       user.setUserName(dto.getUserName());
       user.setFullName(dto.getFullName());
       user.setEmail(dto.getEmail());
       user.setPassword(dto.getPassword());
       user.setIdPlesk(dto.getIdPlesk());

       return user;
    }
}
