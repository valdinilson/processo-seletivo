package br.gov.mt.seplag.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "servidor_efetivo")
@Getter
@Setter
public class ServidorEfetivo extends Pessoa {
    @Column(name = "se_matricula", nullable = false, length = 20)
    private String matricula;
}
