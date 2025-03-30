package br.gov.mt.seplag.service;

import br.gov.mt.seplag.domain.Unidade;
import br.gov.mt.seplag.mapper.UnidadeMapper;
import br.gov.mt.seplag.repository.UnidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnidadeService {

    protected final UnidadeRepository repository;
    protected final UnidadeMapper mapper;

    public Page<Unidade> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Unidade buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidade ID " + id + " não encontrada"));
    }

    public Unidade salvar(Unidade unidade) {
        return repository.save(unidade);
    }

    public void atualizar(Integer id, Unidade unidadeAtualizada) {
        buscarPorId(id);
        unidadeAtualizada.setId(id);
        salvar(unidadeAtualizada);
    }

}
