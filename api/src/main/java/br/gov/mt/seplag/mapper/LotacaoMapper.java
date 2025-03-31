package br.gov.mt.seplag.mapper;

import br.gov.mt.seplag.domain.Lotacao;
import br.gov.mt.seplag.domain.Pessoa;
import br.gov.mt.seplag.domain.Unidade;
import br.gov.mt.seplag.request.LotacaoRequest;
import br.gov.mt.seplag.response.LotacaoResponse;
import org.mapstruct.*;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {PessoaMapper.class, UnidadeMapper.class})
public interface LotacaoMapper {

    @Mapping(target = "dataLotacao", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "dataRemocao", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "pessoa", source = "pessoa", qualifiedByName = "mapIdToPessoa")
    @Mapping(target = "unidade", source = "unidade", qualifiedByName = "mapIdToUnidade")
    Lotacao toEntity(LotacaoRequest lotacaoRequest);

    LotacaoResponse toResponse(Lotacao lotacao);

    @Named("mapIdToPessoa")
    default Pessoa mapPessoa(Integer id) {
        if (id == null) return null;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        return pessoa;
    }

    @Named("mapIdToUnidade")
    default Unidade mapUnidade(Integer id) {
        if (id == null) return null;
        Unidade unidade = new Unidade();
        unidade.setId(id);
        return unidade;
    }

}
