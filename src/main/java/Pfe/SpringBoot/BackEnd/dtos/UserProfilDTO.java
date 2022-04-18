package Pfe.SpringBoot.BackEnd.dtos;


import Pfe.SpringBoot.BackEnd.entities.User;
import lombok.Data;

@Data
public class UserProfilDTO {
    private String username;
    private String fullName;
    private String email;
    private String phone;

    public UserProfilDTO(final User user) {
        this.username = user.getUserName();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }
}
