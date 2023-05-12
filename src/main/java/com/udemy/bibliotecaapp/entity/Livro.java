package com.udemy.bibliotecaapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.udemy.bibliotecaapp.entity.enums.Genero;
import com.udemy.bibliotecaapp.entity.enums.Idioma;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Table(name = "livro")
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private Integer paginas;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private String editora;

    private String autores;

    @Column(name = "data_publicacao")
    private ZonedDateTime dataPublicacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id")
    @JsonBackReference
    private Reserva reserva;
}
