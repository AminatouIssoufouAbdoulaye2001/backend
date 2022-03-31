package Pfe.SpringBoot.BackEnd.dtos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;


public class PaiementDto implements Serializable {
@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
@Column(nullable = false,unique = true)
private Long numCarte;
private LocalDate date_expiration;
private String typePaiement;
private String email;
//  @OneToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "client_id")
//  private ERole ROLE_CLIENT;
}
