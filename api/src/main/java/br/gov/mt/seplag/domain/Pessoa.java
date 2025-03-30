package br.gov.mt.seplag.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private Integer id;

    @Column(name = "pes_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "pes_data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "pes_sexo", nullable = false, length = 9)
    private String sexo;

    @Column(name = "pes_mae", nullable = false, length = 200)
    private String nomeDaMae;

    @Column(name = "pes_pai", length = 200)
    private String nomeDoPai;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    private Set<Foto> fotos;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    private Set<Lotacao> lotacoes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pessoa_endereco",
            joinColumns = @JoinColumn(name = "pes_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    private Set<Endereco> enderecos = new HashSet<>();

    public boolean hasIdEndereco(Integer idEndereco) {
        return enderecos.stream().anyMatch(e -> e.getId().equals(idEndereco));
    }

    public void adicionar(Endereco endereco) {
        this.enderecos.add(endereco);
        endereco.getPessoas().add(this);
    }

    public void removerPorId(Integer id) {
        Optional<Endereco> endereco = buscarPorId(id);

        endereco.ifPresent(e -> {
            this.enderecos.remove(e);
            e.getPessoas().remove(this);
        });
    }

    private Optional<Endereco> buscarPorId(Integer id) {
        return this.enderecos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public Integer getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
