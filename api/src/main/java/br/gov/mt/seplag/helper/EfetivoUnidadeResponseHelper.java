package br.gov.mt.seplag.helper;

import br.gov.mt.seplag.domain.ServidorEfetivo;
import br.gov.mt.seplag.domain.Unidade;
import br.gov.mt.seplag.mapper.ServidorEfetivoMapper;
import br.gov.mt.seplag.response.EfetivoUnidadeResponse;
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

    public Page<EfetivoUnidadeResponse> toResponse(Integer id, Page<ServidorEfetivo> pageServidores) {
        Unidade unidade = unidadeService.buscarPorId(id);

        return pageServidores.map(servidor -> {
            var servidorResponse = mapper.toEfetivoUnidadeResponse(servidor);
            servidorResponse.setUnidade(unidade.getNome());
            servidorResponse.setFotografias(fotoService.obterUrlTemporaria(servidor));
            return servidorResponse;
        });
    }
}
