package Pfe.SpringBoot.BackEnd.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "domaines")
@Data
public class Domaine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private long dateDebut;
    private long dateFin;
    private String statut;
    private float prix;
    private String customer;
    private String adresse;
}
