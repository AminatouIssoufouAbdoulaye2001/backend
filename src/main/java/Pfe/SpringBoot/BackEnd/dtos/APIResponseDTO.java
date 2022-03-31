package Pfe.SpringBoot.BackEnd.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class APIResponseDTO {
    private boolean success = true;
    private String message;
    private Object payload;

    public APIResponseDTO(String message) {
        this.message = message;
    }
    public APIResponseDTO(Object payload) {
        this.payload = payload;
    }
    public APIResponseDTO(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
