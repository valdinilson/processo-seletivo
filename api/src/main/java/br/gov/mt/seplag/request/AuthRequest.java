package br.gov.mt.seplag.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRequest {
    @NotBlank(message = "Preenchimento obrigatório")
    private String usuario;

    @NotBlank(message = "Preenchimento obrigatório")
    private String senha;
}
