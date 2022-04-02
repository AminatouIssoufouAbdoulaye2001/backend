package Pfe.SpringBoot.BackEnd.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NGHostRequestErrorDTO {
    private String status="error";
    private String message;
    private Integer code;

}
