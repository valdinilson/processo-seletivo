package br.gov.mt.seplag.mapper;

import br.gov.mt.seplag.domain.Unidade;
import br.gov.mt.seplag.request.UnidadeRequest;
import br.gov.mt.seplag.response.UnidadeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = EnderecoMapper.class)
public interface UnidadeMapper {

    Unidade toEntity(UnidadeRequest unidadeRequest);

    UnidadeResponse toResponse(Unidade unidade);
}
