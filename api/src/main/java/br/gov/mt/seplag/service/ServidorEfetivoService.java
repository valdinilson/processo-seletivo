package br.gov.mt.seplag.service;

import br.gov.mt.seplag.domain.ServidorEfetivo;
import br.gov.mt.seplag.mapper.ServidorEfetivoMapper;
import br.gov.mt.seplag.repository.ServidorEfetivoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServidorEfetivoService {

    protected final ServidorEfetivoRepository repository;
    protected final ServidorEfetivoMapper mapper;

    public Page<ServidorEfetivo> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ServidorEfetivo buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servidor Efetivo ID " + id + " não encontrado"));
    }

    public ServidorEfetivo salvar(ServidorEfetivo servidorEfetivo) {
        return repository.save(servidorEfetivo);
    }

    public void atualizar(Integer id, ServidorEfetivo servidorEfetivoAtualizado) {
        buscarPorId(id);
        servidorEfetivoAtualizado.setId(id);
        salvar(servidorEfetivoAtualizado);
    }

}
