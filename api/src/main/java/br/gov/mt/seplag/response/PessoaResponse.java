package br.gov.mt.seplag.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PessoaResponse {
    private String nome;
    private String dataNascimento;
    private String sexo;
    private String nomeDaMae;
    private String nomeDoPai;
    private List<EnderecoResponse> enderecos;
}
