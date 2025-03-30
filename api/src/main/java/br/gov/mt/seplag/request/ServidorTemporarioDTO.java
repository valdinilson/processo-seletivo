package br.gov.mt.seplag.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServidorTemporarioDTO extends PessoaDTO {
    @NotBlank(message = "Preenchimento obrigatório")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Data de admissão deve estar no formato dd/MM/yyyy")
    private String dataAdmissao;

    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Data de demissão deve estar no formato dd/MM/yyyy")
    private String dataDemissao;
}
