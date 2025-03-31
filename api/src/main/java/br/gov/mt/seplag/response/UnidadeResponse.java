package br.gov.mt.seplag.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UnidadeResponse {
    private String nome;
    private String sigla;
    private List<EnderecoResponse> enderecos;
}
