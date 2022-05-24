package Pfe.SpringBoot.BackEnd.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "abonnements")
@Data
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ABONNEMENT_ID")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subscribe_services",
            joinColumns = { @JoinColumn(name = "abonnement_id") },
            inverseJoinColumns = { @JoinColumn(name = "service_id") })
    private List<Service> services;

    private  long dateDebut;

    private long dateFin;

    private String customerEmail;

    private float price;
}
