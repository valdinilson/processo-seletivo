package br.gov.mt.seplag.repository;

import br.gov.mt.seplag.domain.ServidorTemporario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporario, Integer> {

}
