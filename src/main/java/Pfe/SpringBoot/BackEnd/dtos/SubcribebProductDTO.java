package Pfe.SpringBoot.BackEnd.dtos;
import Pfe.SpringBoot.BackEnd.entities.Service;
import lombok.Data;

@Data
public class SubcribebProductDTO {
    private long id;

    private String name;

    private String bandwidth;

    private String domain;

    private float price;

    private int nbCore;

    private int nbEmail;

    private String ram;

    private int nbDatabase;

    private String memorySpace;

    private  long dateDebut;

    private long dateFin;

    public SubcribebProductDTO() {

    }

    public  SubcribebProductDTO(Service service) {
        this.id = service.getId();
        this.name = service.getName();
        this.bandwidth = service.getBandwidth();
        this.domain = service.getDomain();
        this.price = service.getPrice();
        this.nbCore = service.getNbCore();
        this.nbEmail = service.getNbEmail();
        this.ram = service.getRam();
        this.nbDatabase = service.getNbDatabase();
        this.memorySpace = service.getMemorySpace();
    }
}
