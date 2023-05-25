package com.udemy.bibliotecaapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    private String nome;

    @Column(name = "data_nascimento")
    private ZonedDateTime dataNascimento;

    @Column(name = "data_criacao")
    private ZonedDateTime dataCriacao;

    private String email;

    @Column(name = "documento_identificacao")
    private String documentoIdentificacao;

    boolean ativo;
}
