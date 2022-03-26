package Pfe.SpringBoot.BackEnd.entities;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "payment")
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String numCarte;
  private String lastName;
  //to do : class enumeration
  private String paymentType;
  private String email;

  @OneToMany(fetch = FetchType.LAZY)
  private Collection<Service> services;

  @ManyToOne(optional = false)
  @JoinColumn(name = "client_id")
  private User client;



}
