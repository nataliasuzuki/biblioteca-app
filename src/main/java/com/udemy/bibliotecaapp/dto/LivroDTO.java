package com.udemy.bibliotecaapp.dto;

import java.time.ZonedDateTime;

public interface LivroDTO {

    Long getId();
    String getAutores();
    ZonedDateTime getDataPublicacao();
    String getEditora();
    String getGenero();
    String getIdioma();
    String getNome();
    Integer getPaginas();
    Long getReservaId();
}
