package br.gov.mt.seplag.repository;

import br.gov.mt.seplag.domain.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    Page<Endereco> findEnderecosByUnidadesId(Integer id, Pageable pageable);

    Page<Endereco> findEnderecosByPessoasId(Integer id, Pageable pageable);
}
