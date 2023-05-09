package com.udemy.bibliotecaapp.service;

import com.udemy.bibliotecaapp.entity.Livro;
import com.udemy.bibliotecaapp.entity.Reserva;
import com.udemy.bibliotecaapp.exception.IdNaoEncontradoException;
import com.udemy.bibliotecaapp.repository.LivroRepository;
import com.udemy.bibliotecaapp.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Reserva save(Reserva reserva) {
        reserva.setDataReserva(ZonedDateTime.now());
        reserva.setDataDevolucao(calcularPrazoDevolucao());
        Reserva reservaSalvo = reservaRepository.save(reserva);

        List<Livro> livros = reservaSalvo.getLivros();
        for(Livro livro : livros) {
            Optional<Livro> produtoOptional = livroRepository.findById(livro.getId());
            if(produtoOptional.isPresent()) {
                produtoOptional.get().setReserva(reservaSalvo);
                livroRepository.save(produtoOptional.get());
            }
        }
        return reservaSalvo;
    }

    private ZonedDateTime calcularPrazoDevolucao() {
        return ZonedDateTime.now().plusDays(7);
    }

    public Reserva update(Reserva reserva) {
        if(reservaRepository.findById(reserva.getId()).isEmpty()) {
           throw new IdNaoEncontradoException("ID não encontrado!");
        }
        return reservaRepository.save(reserva);
    }

    public boolean delete(Long id) {
        if(reservaRepository.findById(id).isEmpty()) {
            throw new IdNaoEncontradoException("ID não encontrado!");
        }
        reservaRepository.deleteById(id);
        return reservaRepository.findById(id).isEmpty();
    }
}
