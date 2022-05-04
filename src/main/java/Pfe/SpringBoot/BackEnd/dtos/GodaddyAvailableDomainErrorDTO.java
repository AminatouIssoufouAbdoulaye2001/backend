package Pfe.SpringBoot.BackEnd.dtos;

import lombok.Data;

@Data
public class GodaddyAvailableDomainErrorDTO {
    private String code;
    private String domain;
    private String message;
    private String path;
    private int status;
}
