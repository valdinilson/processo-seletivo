package br.gov.mt.seplag.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private int id;

    @Column(name = "usu_nome", nullable = false, length = 20)
    private String nome;

    @Column(name = "usu_email", nullable = false, length = 80)
    private String email;

    @Column(name = "usu_senha", nullable = false, length = 200)
    private String senha;
}
