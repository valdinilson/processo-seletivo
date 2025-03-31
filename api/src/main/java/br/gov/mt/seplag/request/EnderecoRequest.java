package br.gov.mt.seplag.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoRequest {
    @NotBlank(message = "Preenchimento obrigatório")
    @Size(max = 50, message = "Tamanho máximo excedido")
    private String tipoLogradouro;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(max = 200, message = "Tamanho máximo excedido")
    private String logradouro;

    @NotNull(message = "Preenchimento obrigatório")
    @Min(value = 1, message = "Valor inválido")
    @Max(value = 99999, message = "Valor inválido")
    private Short numero;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(max = 100, message = "Tamanho máximo excedido")
    private String bairro;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(max = 200, message = "Tamanho máximo excedido")
    private String cidade;

    @NotBlank(message = "Preenchimento obrigatório")
    @Pattern(regexp = "^[A-Z]{2}$", message = "UF deve conter exatamente 2 letras maiúsculas.")
    private String uf;
}
