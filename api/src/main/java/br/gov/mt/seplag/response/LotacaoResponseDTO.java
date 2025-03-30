package br.gov.mt.seplag.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LotacaoResponseDTO {
    private String dataLotacao;
    private String dataRemocao;
    private String portaria;
    private PessoaResponseDTO pessoa;
    private UnidadeResponseDTO unidade;
}
