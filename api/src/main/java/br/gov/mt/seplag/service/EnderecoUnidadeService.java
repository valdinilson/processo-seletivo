package br.gov.mt.seplag.service;

import br.gov.mt.seplag.domain.Endereco;
import br.gov.mt.seplag.domain.Unidade;
import br.gov.mt.seplag.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnderecoUnidadeService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoService enderecoService;
    private final UnidadeService unidadeService;

    public Page<Endereco> listarPorUnidadeId(Integer id, Pageable pageable) {
        unidadeService.buscarPorId(id);
        return enderecoRepository.findEnderecosByUnidadesId(id, pageable);
    }

    public Endereco salvarPorUnidade(Integer id, Endereco endereco) {
        Unidade unidade = unidadeService.buscarPorId(id);
        unidade.adicionar(endereco);
        return enderecoService.salvar(endereco);
    }

    public void atualizarPorUnidade(Integer idUnidade, Integer idEndereco, Endereco enderecoAtualizado) {
        Unidade unidade = unidadeService.buscarPorId(idUnidade);
        enderecoService.buscarPorId(idEndereco);

        if (!unidade.hasIdEndereco(idEndereco)) {
            throw new EntityNotFoundException("Endereço ID " + idEndereco + " não encontrado para a Unidade.");
        }

        enderecoAtualizado.setId(idEndereco);

        enderecoService.salvar(enderecoAtualizado);
    }

}
