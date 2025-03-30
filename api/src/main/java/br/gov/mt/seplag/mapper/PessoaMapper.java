package br.gov.mt.seplag.mapper;

import br.gov.mt.seplag.domain.Pessoa;
import br.gov.mt.seplag.request.PessoaDTO;
import br.gov.mt.seplag.response.PessoaResponseDTO;
import br.gov.mt.seplag.response.ResponsePostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = EnderecoMapper.class)
public interface PessoaMapper {

    @Mapping(target = "dataNascimento", dateFormat = "dd/MM/yyyy")
    Pessoa toEntity(PessoaDTO dto);

    ResponsePostDTO toResponsePostDTO(Pessoa entity);

    PessoaResponseDTO toResponseDTO(Pessoa entity);
}
