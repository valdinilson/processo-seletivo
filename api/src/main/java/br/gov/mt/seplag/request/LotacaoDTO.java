package br.gov.mt.seplag.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LotacaoDTO {
    @NotBlank(message = "Preenchimento obrigatório")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Data de lotação deve estar no formato dd/MM/yyyy")
    private String dataLotacao;

    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Data de remoção deve estar no formato dd/MM/yyyy")
    private String dataRemocao;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(max = 100, message = "Tamanho máximo excedido")
    private String portaria;

    @NotNull(message = "ID da pessoa é obrigatório")
    private Integer pessoa;

    @NotNull(message = "ID da unidade é obrigatório")
    private Integer unidade;
}
