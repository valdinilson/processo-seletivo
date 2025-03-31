package br.gov.mt.seplag.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServidorEfetivoRequest extends PessoaRequest {
    @NotBlank(message = "Preenchimento obrigatório")
    @Size(max = 20, message = "Tamanho máximo excedido")
    private String matricula;
}
