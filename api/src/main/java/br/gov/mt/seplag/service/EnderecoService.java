package br.gov.mt.seplag.service;

import br.gov.mt.seplag.domain.Cidade;
import br.gov.mt.seplag.domain.Endereco;
import br.gov.mt.seplag.repository.CidadeRepository;
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
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final CidadeRepository cidadeRepository;

    public Page<Endereco> listarTodos(Pageable pageable) {
        return enderecoRepository.findAll(pageable);
    }

    public Endereco buscarPorId(Integer id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço ID " + id + " não encontrado"));
    }

    public Endereco salvar(Endereco endereco) {
        endereco.setCidade(persistirCidadeSeNecessario(endereco.getCidade()));

        return enderecoRepository.save(endereco);
    }

    public void atualizar(Integer id, Endereco enderecoAtualizado) {
        buscarPorId(id);
        enderecoAtualizado.setId(id);
        enderecoAtualizado.setCidade(persistirCidadeSeNecessario(enderecoAtualizado.getCidade()));

        enderecoRepository.save(enderecoAtualizado);
    }

    /**
     * Verifica se a cidade já existe no banco. Se não, salva uma nova instância.
     */
    private Cidade persistirCidadeSeNecessario(Cidade cidade) {
        return cidadeRepository.findByNomeAndUf(cidade.getNome(), cidade.getUf())
                .orElseGet(() -> cidadeRepository.save(cidade));
    }

}
