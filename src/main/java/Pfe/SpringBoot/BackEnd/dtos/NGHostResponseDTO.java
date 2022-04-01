package Pfe.SpringBoot.BackEnd.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class NGHostResponseDTO {
    private boolean success = true;
    private String message;
    private Object payload;

    public NGHostResponseDTO(String message) {
        this.message = message;
    }
    public NGHostResponseDTO(Object payload) {
        this.payload = payload;
    }
    public NGHostResponseDTO(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
