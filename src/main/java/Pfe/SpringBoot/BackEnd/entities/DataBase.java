package Pfe.SpringBoot.BackEnd.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "databases")
public class DataBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private  String nomUtilisateur;

    private String motdePasse;

    private String adresseHote;
}
