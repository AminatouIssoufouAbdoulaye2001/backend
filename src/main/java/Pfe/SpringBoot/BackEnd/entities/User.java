package Pfe.SpringBoot.BackEnd.entities;

import Pfe.SpringBoot.BackEnd.constantes.ERole;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity(name = "user")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String firstName;
  @Column(nullable = false)
  private String lastName;
  @Column(nullable = false, unique = true)
  private String email;
  @Column(nullable = false)
  private String phone;
  private String password;
  @Enumerated(EnumType.STRING)
  private ERole role;

  @OneToMany(fetch = FetchType.LAZY)
  private Collection<Ticket> tickets;

  @OneToMany(fetch = FetchType.LAZY)
  private Collection<Facture> factures;

}
