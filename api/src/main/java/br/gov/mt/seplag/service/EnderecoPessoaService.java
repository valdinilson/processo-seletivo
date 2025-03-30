package br.gov.mt.seplag.service;

import br.gov.mt.seplag.domain.Endereco;
import br.gov.mt.seplag.domain.Pessoa;
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
public class EnderecoPessoaService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoService enderecoService;
    private final PessoaService pessoaService;

    public Page<Endereco> listarPorPessoaId(Integer id, Pageable pageable) {
        return enderecoRepository.findEnderecosByPessoasId(id, pageable);
    }

    public Endereco salvarPorPessoa(Integer id, Endereco endereco) {
        Pessoa pessoa = pessoaService.buscarPorId(id);
        pessoa.adicionar(endereco);
        return enderecoService.salvar(endereco);
    }

    public void atualizarPorPessoa(Integer idPessoa, Integer idEndereco, Endereco enderecoAtualizado) {
        Pessoa pessoa = pessoaService.buscarPorId(idPessoa);
        enderecoService.buscarPorId(idEndereco);

        if (!pessoa.hasIdEndereco(idEndereco)) {
            throw new EntityNotFoundException("Endereço ID " + idEndereco + " não encontrado para o Servidor.");
        }

        enderecoAtualizado.setId(idEndereco);

        enderecoService.salvar(enderecoAtualizado);
    }

}
