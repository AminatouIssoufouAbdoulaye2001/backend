package Pfe.SpringBoot.BackEnd.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


@Entity(name = "catalogues")
@Data
public class Catalogue {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String name;

  private Date validityDate;

  private String commente;


}