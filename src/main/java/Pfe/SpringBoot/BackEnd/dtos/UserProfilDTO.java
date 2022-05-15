package Pfe.SpringBoot.BackEnd.dtos;

import Pfe.SpringBoot.BackEnd.entities.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserProfilDTO {
    private long id;

    private long idPlesk;

    @NotBlank(message = "le username est obligatoire")
    private String username;

    @NotBlank(message = "le nom & et le prénom sont obligatoires")
    private String fullName;

    @NotBlank(message = "l'adresse email est obligatoire")
    @Email(message = "le format de l'adresse n'est pas valid")
    private String email;

    private String phone;

    private String img;

    public UserProfilDTO() {

    }

    public UserProfilDTO(final User user) {
        this.id = user.getId();
        this.username = user.getUserName();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.idPlesk = user.getIdPlesk();
        this.img = user.getImg();
    }
}
