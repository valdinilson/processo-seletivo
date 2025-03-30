package br.gov.mt.seplag.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServidorTemporarioResponseDTO extends PessoaResponseDTO {
    private String dataAdmissao;
    private String dataDemissao;
}
