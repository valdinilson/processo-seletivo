package br.gov.mt.seplag.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "cidade")
@Getter
@Setter
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid_id")
    private Integer id;

    @Column(name = "cid_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "cid_uf", nullable = false, length = 2)
    private String uf;

    @OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY)
    private Set<Endereco> enderecos;
}
