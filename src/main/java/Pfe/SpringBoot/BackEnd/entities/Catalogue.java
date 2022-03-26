package Pfe.SpringBoot.BackEnd.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;


@Entity(name = "catalogue")
@Data
public class Catalogue {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String name;
  private Date validityDate;
  private String commente;

  @OneToMany(cascade = ALL, fetch = LAZY)
  private Collection<Offer> offers;


}