package br.gov.mt.seplag.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PessoaDTO {

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(max = 200, message = "Tamanho máximo excedido")
    private String nome;

    @NotBlank(message = "Preenchimento obrigatório")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Data de nascimento deve estar no formato dd/MM/yyyy")
    private String dataNascimento;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(max = 9, message = "Tamanho máximo excedido")
    private String sexo;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(max = 200, message = "Tamanho máximo excedido")
    private String nomeDaMae;

    @Size(max = 200, message = "Tamanho máximo excedido")
    private String nomeDoPai;
}
