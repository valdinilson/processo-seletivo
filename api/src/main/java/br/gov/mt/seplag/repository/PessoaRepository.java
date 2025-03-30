package br.gov.mt.seplag.repository;

import br.gov.mt.seplag.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
