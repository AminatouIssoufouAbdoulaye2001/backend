package Pfe.SpringBoot.BackEnd.entities;

import Pfe.SpringBoot.BackEnd.constantes.ERole;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "user")
@Data
public class User {

    private long idPlesk;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    @Column(nullable = false)

    private String fullName;
    @Column(nullable = false)

    private String email;

    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;

    private boolean isActive;
}
