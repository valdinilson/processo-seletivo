package br.gov.mt.seplag.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoResponseDTO {
    private String logradouro;
    private Short numero;
    private String bairro;
    private String cidade;
    private String uf;
}
