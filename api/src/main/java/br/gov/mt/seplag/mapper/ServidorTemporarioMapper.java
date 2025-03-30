package br.gov.mt.seplag.mapper;

import br.gov.mt.seplag.domain.ServidorTemporario;
import br.gov.mt.seplag.request.ServidorTemporarioDTO;
import br.gov.mt.seplag.response.ResponsePostDTO;
import br.gov.mt.seplag.response.ServidorTemporarioResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = EnderecoMapper.class)
public interface ServidorTemporarioMapper {

    @Mapping(target = "dataNascimento", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "dataAdmissao", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "dataDemissao", dateFormat = "dd/MM/yyyy")
    ServidorTemporario toEntity(ServidorTemporarioDTO dto);

    ResponsePostDTO toResponsePostDTO(ServidorTemporario entity);

    ServidorTemporarioResponseDTO toResponseDTO(ServidorTemporario entity);
}
