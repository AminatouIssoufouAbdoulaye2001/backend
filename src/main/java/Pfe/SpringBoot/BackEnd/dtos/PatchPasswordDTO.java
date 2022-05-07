package Pfe.SpringBoot.BackEnd.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class PatchPasswordDTO {
    private long userId;

    @NotBlank(message = "le nouveau mot de passe est obligatoire")
    @NotEmpty(message = "le nouveau mot de passe est obligatoire")
    private String newPassword;

    @NotBlank(message = "le mot de passe de confirmation est obligatoire")
    @NotEmpty(message = "le nouveau mot de passe de confirmation est obligatoire")
    private String confNewPassword;
}
