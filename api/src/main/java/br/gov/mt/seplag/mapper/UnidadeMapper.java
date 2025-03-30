package br.gov.mt.seplag.mapper;

import br.gov.mt.seplag.domain.Unidade;
import br.gov.mt.seplag.request.UnidadeDTO;
import br.gov.mt.seplag.response.ResponsePostDTO;
import br.gov.mt.seplag.response.UnidadeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = EnderecoMapper.class)
public interface UnidadeMapper {

    Unidade toEntity(UnidadeDTO dto);

    ResponsePostDTO toResponsePostDTO(Unidade unidade);

    UnidadeResponseDTO toResponseDTO(Unidade entity);
}
