package Pfe.SpringBoot.BackEnd.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class NGHostRequestErrorDTO {
    private int status;
    private String message;
}
