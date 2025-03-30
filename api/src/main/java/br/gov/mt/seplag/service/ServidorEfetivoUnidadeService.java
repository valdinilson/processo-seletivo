package br.gov.mt.seplag.service;

import br.gov.mt.seplag.domain.Endereco;
import br.gov.mt.seplag.domain.ServidorEfetivo;
import br.gov.mt.seplag.repository.ServidorEfetivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServidorEfetivoUnidadeService {

    private final UnidadeService unidadeService;
    private final ServidorEfetivoRepository servidorEfetivoRepository;

    public Page<ServidorEfetivo> listarEfetivosPorUnidade(Integer unidadeId, Pageable pageable) {
        unidadeService.buscarPorId(unidadeId);
        return servidorEfetivoRepository.findEfetivosByUnidadeId(unidadeId, pageable);
    }

    public Page<Endereco> listarEnderecoUnidadePorServidor(String parteNome, Pageable pageable) {
        return servidorEfetivoRepository.findEnderecoUnidadeByServidorNome(parteNome, pageable);
    }
}
