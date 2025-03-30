package br.gov.mt.seplag.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "unidade")
@Getter
@Setter
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unid_id")
    private Integer id;

    @Column(name = "unid_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "unid_sigla", nullable = false, length = 20)
    private String sigla;

    @OneToMany(mappedBy = "unidade", fetch = FetchType.LAZY)
    private Set<Lotacao> lotacoes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "unidade_endereco",
            joinColumns = @JoinColumn(name = "unid_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    private Set<Endereco> enderecos = new HashSet<>();

    public boolean hasIdEndereco(Integer idEndereco) {
        return enderecos.stream().anyMatch(e -> e.getId().equals(idEndereco));
    }

    public void adicionar(Endereco endereco) {
        this.enderecos.add(endereco);
        endereco.getUnidades().add(this);
    }

    public void removerPorId(Integer id) {
        Optional<Endereco> endereco = buscarPorId(id);

        endereco.ifPresent(e -> {
            this.enderecos.remove(e);
            e.getUnidades().remove(this);
        });
    }

    private Optional<Endereco> buscarPorId(Integer id) {
        return this.enderecos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }
}
