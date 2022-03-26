package Pfe.SpringBoot.BackEnd.dtos;

import Pfe.SpringBoot.BackEnd.constantes.ERole;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name ="paiement")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class PaiementDto implements Serializable {
@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
@Column(nullable = false,unique = true)
private Long numCarte;
private LocalDate date_expiration;
private String typePaiement;
private String email;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id")
  private ERole ROLE_CLIENT;
}
