package br.gov.mt.seplag.helper;

import br.gov.mt.seplag.domain.ServidorEfetivo;
import br.gov.mt.seplag.domain.Unidade;
import br.gov.mt.seplag.mapper.ServidorEfetivoMapper;
import br.gov.mt.seplag.response.EfetivoUnidadeResponseDTO;
import br.gov.mt.seplag.service.FotoService;
import br.gov.mt.seplag.service.UnidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EfetivoUnidadeResponseHelper {

    private final UnidadeService unidadeService;
    private final FotoService fotoService;
    private final ServidorEfetivoMapper mapper;

    public Page<EfetivoUnidadeResponseDTO> toDTO(Integer id, Page<ServidorEfetivo> servidores) {
        Unidade unidade = unidadeService.buscarPorId(id);

        return servidores.map(servidor -> {
            var servidorDTO = mapper.toEfetivoUnidadeResponseDTO(servidor);
            servidorDTO.setUnidade(unidade.getNome());
            servidorDTO.setFotografias(fotoService.obterUrlTemporaria(servidor));
            return servidorDTO;
        });
    }
}
