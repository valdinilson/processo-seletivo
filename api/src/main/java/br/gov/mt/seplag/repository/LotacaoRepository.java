package br.gov.mt.seplag.repository;

import br.gov.mt.seplag.domain.Lotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Integer> {

}