package br.gov.mt.seplag.mapper;

import br.gov.mt.seplag.domain.Usuario;
import br.gov.mt.seplag.request.UsuarioRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {
    @Mapping(target = "nome", source = "usuario")
    Usuario toEntity(UsuarioRequest usuarioRequest);
}
