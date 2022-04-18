package Pfe.SpringBoot.BackEnd.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "service")
public class Service {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private int nbrecoeur;

  private String memorySpace;

  private String adressIp;

  private String bandwidth;

  private int Licence;

  private String os;

  private float price;
}

