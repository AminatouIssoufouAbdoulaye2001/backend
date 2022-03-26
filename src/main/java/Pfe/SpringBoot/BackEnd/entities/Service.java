package Pfe.SpringBoot.BackEnd.entities;

import Pfe.SpringBoot.BackEnd.constantes.ERole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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

