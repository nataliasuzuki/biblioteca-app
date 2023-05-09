package com.udemy.bibliotecaapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "reserva")
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoInformal;

    @OneToMany(mappedBy = "reserva")
    private List<Livro> livros;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "data_reserva")
    private ZonedDateTime dataReserva;

    @Column(name = "data_devolucao")
    private ZonedDateTime dataDevolucao;
}
