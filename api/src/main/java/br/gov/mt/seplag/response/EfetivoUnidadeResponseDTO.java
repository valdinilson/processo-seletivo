package br.gov.mt.seplag.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EfetivoUnidadeResponseDTO {
    private String nome;
    private Integer idade;
    private String unidade;
    private List<FotoResponseDTO> fotografias;
}
