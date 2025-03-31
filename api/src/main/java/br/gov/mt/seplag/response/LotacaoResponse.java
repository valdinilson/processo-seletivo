package br.gov.mt.seplag.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LotacaoResponse {
    private String dataLotacao;
    private String dataRemocao;
    private String portaria;
    private PessoaResponse pessoa;
    private UnidadeResponse unidade;
}
