package com.udemy.bibliotecaapp.dto;

import java.time.ZonedDateTime;

public interface LivroReservadoDTO {

    String getNome();
    ZonedDateTime getDataReserva();
    ZonedDateTime getDataDevolucao();
}
