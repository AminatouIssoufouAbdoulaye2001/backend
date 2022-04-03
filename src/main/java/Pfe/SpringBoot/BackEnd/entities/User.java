package Pfe.SpringBoot.BackEnd.entities;

import Pfe.SpringBoot.BackEnd.constantes.ERole;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    @Column(nullable = false)

    private String fullName;
    @Column(nullable = false, unique = true)

    private String email;

    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Ticket> tickets;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Facture> factures;
}
