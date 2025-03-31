package br.gov.mt.seplag.mapper;

import br.gov.mt.seplag.domain.ServidorEfetivo;
import br.gov.mt.seplag.request.ServidorEfetivoRequest;
import br.gov.mt.seplag.response.EfetivoUnidadeResponse;
import br.gov.mt.seplag.response.ServidorEfetivoResponse;
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
    ServidorEfetivo toEntity(ServidorEfetivoRequest servidorEfetivoRequest);

    @Mapping(target = "unidade", ignore = true)
    @Mapping(target = "fotografias", ignore = true)
    EfetivoUnidadeResponse toEfetivoUnidadeResponse(ServidorEfetivo servidorEfetivo);

    ServidorEfetivoResponse toResponse(ServidorEfetivo servidorEfetivo);
}
