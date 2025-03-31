package br.gov.mt.seplag.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRequest {
    @NotBlank(message = "Preenchimento obrigatório")
    @Size(max = 20, message = "Tamanho máximo excedido")
    private String usuario;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(max = 80, message = "Tamanho máximo excedido")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min = 8, max = 50, message = "A senha deve ter entre 8 e 50 caracteres")
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,50}$",
            message = "A senha deve conter letras, números e caracteres especiais"
    )
    private String senha;
}