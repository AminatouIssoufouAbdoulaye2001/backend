package Pfe.SpringBoot.BackEnd.dtos;

import Pfe.SpringBoot.BackEnd.entities.Service;
import lombok.Data;

@Data
public class GetServiceDTO {

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

    public GetServiceDTO() {

    }

    public GetServiceDTO(Service service) {

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

    public void getServiceDTO(Service service) {
        service.setName(this.name);
        service.setDomain(this.domain);
        service.setBandwidth(this.bandwidth);
        service.setPrice(this.price);
        service.setNbCore(this.nbCore);
        service.setNbEmail(this.nbEmail);
        service.setRam(this.ram);
        service.setNbDatabase(this.nbDatabase);
        service.setMemorySpace(this.memorySpace);
    }
}
