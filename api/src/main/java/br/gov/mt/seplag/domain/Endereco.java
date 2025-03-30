package br.gov.mt.seplag.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "endereco")
@Getter
@Setter
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_id")
    private Integer id;

    @Column(name = "end_tipo_logradouro", nullable = false, length = 50)
    private String tipoLogradouro;

    @Column(name = "end_logradouro", nullable = false, length = 200)
    private String logradouro;

    @Column(name = "end_numero", nullable = false)
    private Short numero;

    @Column(name = "end_bairro", nullable = false, length = 100)
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cid_id", nullable = false)
    private Cidade cidade;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "enderecos")
    private Set<Pessoa> pessoas = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "enderecos")
    private Set<Unidade> unidades = new HashSet<>();
}
