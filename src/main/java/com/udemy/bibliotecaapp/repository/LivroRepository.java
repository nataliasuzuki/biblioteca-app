package com.udemy.bibliotecaapp.repository;

import com.udemy.bibliotecaapp.dto.LivroDTO;
import com.udemy.bibliotecaapp.dto.LivroReservadoDTO;
import com.udemy.bibliotecaapp.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query(value = "SELECT l.nome AS nome," +
            "r.dataReserva AS dataReserva," +
            "r.dataDevolucao AS dataDevolucao " +
            "FROM Livro l " +
            "JOIN Reserva r ON r.id = l.reserva.id " +
            "WHERE l.reserva.id IS NOT NULL")
    List<LivroReservadoDTO> findLivrosReservados();

    @Query(value = "SELECT l.id AS id, " +
            "l.autores AS autores, " +
            "l.dataPublicacao AS dataPublicacao, " +
            "l.editora AS editora, " +
            "l.genero AS genero, " +
            "l.idioma AS idioma, " +
            "l.nome AS nome, " +
            "l.paginas AS paginas, " +
            "l.reserva.id AS reservaId " +
            "FROM Livro l")
    List<LivroDTO> findAllLivros();
}
