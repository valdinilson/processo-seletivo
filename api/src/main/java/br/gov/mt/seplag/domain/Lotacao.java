package br.gov.mt.seplag.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "lotacao")
@Getter
@Setter
public class Lotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Integer id;

    @Column(name = "lot_data_lotacao", nullable = false)
    private LocalDate dataLotacao;

    @Column(name = "lot_data_remocao")
    private LocalDate dataRemocao;

    @Column(name = "lot_portaria", nullable = false, length = 100)
    private String portaria;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unid_id", nullable = false)
    private Unidade unidade;
}
