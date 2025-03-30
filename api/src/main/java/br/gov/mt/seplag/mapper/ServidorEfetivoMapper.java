package br.gov.mt.seplag.mapper;

import br.gov.mt.seplag.domain.ServidorEfetivo;
import br.gov.mt.seplag.request.ServidorEfetivoDTO;
import br.gov.mt.seplag.response.EfetivoUnidadeResponseDTO;
import br.gov.mt.seplag.response.ResponsePostDTO;
import br.gov.mt.seplag.response.ServidorEfetivoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = EnderecoMapper.class)
public interface ServidorEfetivoMapper {

    @Mapping(target = "dataNascimento", dateFormat = "dd/MM/yyyy")
    ServidorEfetivo toEntity(ServidorEfetivoDTO dto);

    @Mapping(target = "unidade", ignore = true)
    @Mapping(target = "fotografias", ignore = true)
    EfetivoUnidadeResponseDTO toEfetivoUnidadeResponseDTO(ServidorEfetivo efetivo);

    ResponsePostDTO toResponsePostDTO(ServidorEfetivo entity);

    ServidorEfetivoResponseDTO toResponseDTO(ServidorEfetivo entity);
}
