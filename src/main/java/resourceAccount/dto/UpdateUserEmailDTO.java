package resourceAccount.dto;

import resourceAccount.validation.UniqueEmail;
import resourceAccount.validation.ValidEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserEmailDTO {

    private Long id;
    @NotBlank(message = "O email é obrigatório; ")
    @Size(min = 1, max = 255, message = "O nome deve ter entre 1 e 255 caracteres")
    @ValidEmail
    @UniqueEmail
    private String email;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
