package br.gov.mt.seplag.mapper;

import br.gov.mt.seplag.domain.Endereco;
import br.gov.mt.seplag.request.EnderecoRequest;
import br.gov.mt.seplag.response.EnderecoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnderecoMapper {

    @Mapping(target = "cidade.nome", source = "cidade")
    @Mapping(target = "cidade.uf", source = "uf")
    Endereco toEntity(EnderecoRequest enderecoRequest);

    @Mapping(
            target = "logradouro",
            expression = "java(concatTipoLogradouro(endereco.getTipoLogradouro(), endereco.getLogradouro()))"
    )
    @Mapping(target = "cidade", source = "cidade.nome")
    @Mapping(target = "uf", source = "cidade.uf")
    EnderecoResponse toResponse(Endereco endereco);

    default String concatTipoLogradouro(String tipo, String logradouro) {
        if (tipo == null) tipo = "";
        if (logradouro == null) logradouro = "";
        return (tipo + " " + logradouro).trim();
    }

}
