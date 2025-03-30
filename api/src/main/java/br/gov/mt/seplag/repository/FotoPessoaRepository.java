package br.gov.mt.seplag.repository;

import br.gov.mt.seplag.domain.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoPessoaRepository extends JpaRepository<Foto, Integer> {

}
