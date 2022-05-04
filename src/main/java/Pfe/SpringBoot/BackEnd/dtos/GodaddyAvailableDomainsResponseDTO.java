package Pfe.SpringBoot.BackEnd.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GodaddyAvailableDomainsResponseDTO {
    private List<Godaddy> domains;

    private List<GodaddyAvailableDomainErrorDTO> errors;
}
