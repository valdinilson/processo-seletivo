package br.gov.mt.seplag.service;

import br.gov.mt.seplag.domain.ServidorTemporario;
import br.gov.mt.seplag.mapper.ServidorTemporarioMapper;
import br.gov.mt.seplag.repository.ServidorTemporarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServidorTemporarioService {

    protected final ServidorTemporarioRepository repository;
    protected final ServidorTemporarioMapper mapper;

    public Page<ServidorTemporario> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ServidorTemporario buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servidor Temporário ID " + id + " não encontrado"));
    }

    public ServidorTemporario salvar(ServidorTemporario servidorTemporario) {
        return repository.save(servidorTemporario);
    }

    public void atualizar(Integer id, ServidorTemporario servidorTemporarioAtualizado) {
        buscarPorId(id);
        servidorTemporarioAtualizado.setId(id);
        salvar(servidorTemporarioAtualizado);
    }

}
