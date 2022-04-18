package Pfe.SpringBoot.BackEnd.entities;

import Pfe.SpringBoot.BackEnd.constantes.ProductType;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "abonnements")
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomDuProduit;

    @Enumerated(EnumType.STRING)
    private ProductType typeProduit;

    private Date dateDebut;

    private Date dateFin;

    private boolean isPaid;
}
