package Pfe.SpringBoot.BackEnd.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity(name = "offer")
@Data
public class Offer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
private String name;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Collection<Service> services;

}
