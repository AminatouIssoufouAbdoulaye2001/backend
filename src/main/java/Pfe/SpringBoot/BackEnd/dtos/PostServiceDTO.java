package Pfe.SpringBoot.BackEnd.dtos;

import Pfe.SpringBoot.BackEnd.entities.Service;
import lombok.Data;

@Data
public class PostServiceDTO {

    private String name;

    private String bandwidth;

    private String domain;

    private float price;

    private int nbCore;

    private int nbEmail;


    private int nbDatabase;
    private String ram;
    private String memorySpace;

    public PostServiceDTO() {

    }

    public PostServiceDTO(Service service) {

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

    public Service getServiceFromDTO() {

        Service service = new Service();
        service.setName(this.getName());
        service.setDomain(this.getDomain());
        service.setPrice(this.getPrice());
        service.setNbCore(this.getNbCore());
        service.setNbEmail(this.getNbEmail());
        service.setRam(this.getRam());
        service.setNbDatabase(this.getNbDatabase());
        service.setMemorySpace(this.getMemorySpace());
        service.setBandwidth(this.getBandwidth());
        return service;
    }
}

