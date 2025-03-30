package br.gov.mt.seplag.service;

import br.gov.mt.seplag.domain.Endereco;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnderecoFuncionalService {

    private final ServidorEfetivoUnidadeService servidorEfetivoUnidadeService;

    public Page<Endereco> listarEnderecoFuncionalPorServidorNome(String nome, Pageable pageable) {
        return servidorEfetivoUnidadeService.listarEnderecoUnidadePorServidor(nome, pageable);
    }

}
