package Pfe.SpringBoot.BackEnd.dtos;

import Pfe.SpringBoot.BackEnd.entities.Abonnement;
import lombok.Data;

@Data
public class GetAbonnement {
    private long id;

    private String name;

    private float price;

    private String customerEmail;

    private long startedDate;

    private long endedDate;

    public GetAbonnement() {

    }

    public GetAbonnement(Abonnement subcription) {
        this.id = subcription.getId();
        this.name = subcription.getServices().get(0).getName();
        this.price = subcription.getPrice();
        this.customerEmail = subcription.getCustomerEmail();
        this.startedDate = subcription.getDateDebut();
        this.endedDate = subcription.getDateFin();
    }
}
