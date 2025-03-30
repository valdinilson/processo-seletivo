package br.gov.mt.seplag.repository;

import br.gov.mt.seplag.domain.Endereco;
import br.gov.mt.seplag.domain.ServidorEfetivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Integer> {

    @Query("""
                SELECT se
                    FROM ServidorEfetivo se
                    JOIN Lotacao l ON l.pessoa.id = se.id
                    WHERE l.unidade.id = :unidadeId
            """)
    Page<ServidorEfetivo> findEfetivosByUnidadeId(@Param("unidadeId") Integer unidadeId, Pageable pageable);

    @Query("""
              SELECT e
                    FROM ServidorEfetivo se
                    JOIN se.lotacoes lot
                    JOIN lot.unidade un
                    JOIN un.enderecos e
                    JOIN e.cidade c
                    WHERE se.nome LIKE %:nome%
            """)
    Page<Endereco> findEnderecoUnidadeByServidorNome(@Param("nome") String nome, Pageable pageable);
}
