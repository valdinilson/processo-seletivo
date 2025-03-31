package br.gov.mt.seplag.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServidorTemporarioResponse extends PessoaResponse {
    private String dataAdmissao;
    private String dataDemissao;
}
