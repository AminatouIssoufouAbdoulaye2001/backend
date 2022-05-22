package Pfe.SpringBoot.BackEnd.entities;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "services")
@Data
public class Service {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "SERVICE_ID")
  private Long id;

  private String name;

  private String bandwidth;

  private String domain;

  private float price;

  private int nbCore;

  private int nbEmail;

  private String ram;

  private int nbDatabase;

  private String memorySpace;

}

