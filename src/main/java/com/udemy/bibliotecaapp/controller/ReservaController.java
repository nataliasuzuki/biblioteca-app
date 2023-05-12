package com.udemy.bibliotecaapp.controller;

import com.udemy.bibliotecaapp.entity.Reserva;
import com.udemy.bibliotecaapp.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity buscarTodasReservas() {
        return new ResponseEntity<>(reservaService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity criarReserva(@Valid @RequestBody Reserva reserva) {
        return new ResponseEntity<>(reservaService.save(reserva), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity alterarReserva(@Valid @RequestBody Reserva reserva) {
        return new ResponseEntity<>(reservaService.update(reserva), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removerReserva(@PathVariable Long id) {
        boolean sucesso = reservaService.delete(id);
        if(sucesso)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
