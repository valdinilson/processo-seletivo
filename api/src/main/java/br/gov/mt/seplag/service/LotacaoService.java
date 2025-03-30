package br.gov.mt.seplag.service;

import br.gov.mt.seplag.domain.Lotacao;
import br.gov.mt.seplag.mapper.LotacaoMapper;
import br.gov.mt.seplag.repository.LotacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LotacaoService {

    protected final LotacaoRepository repository;
    protected final LotacaoMapper mapper;

    public Page<Lotacao> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Lotacao buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lotação ID " + id + " não encontrada"));
    }

    public Lotacao salvar(Lotacao lotacao) {
        return repository.save(lotacao);
    }

    public void atualizar(Integer id, Lotacao lotacao) {
        buscarPorId(id);
        lotacao.setId(id);
        salvar(lotacao);
    }

}