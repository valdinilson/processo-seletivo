package br.gov.mt.seplag.service;

import br.gov.mt.seplag.domain.Pessoa;
import br.gov.mt.seplag.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaService {

    protected final PessoaRepository pessoaRepository;

    public Pessoa buscarPorId(Integer id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servidor ID " + id + " não encontrado"));
    }

}
