package br.gov.mt.seplag.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServidorEfetivoResponseDTO extends PessoaResponseDTO {
    private String matricula;
}
