package com.udemy.bibliotecaapp.service;

import com.udemy.bibliotecaapp.entity.Livro;
import com.udemy.bibliotecaapp.entity.Reserva;
import com.udemy.bibliotecaapp.entity.Usuario;
import com.udemy.bibliotecaapp.exception.IdNaoEncontradoException;
import com.udemy.bibliotecaapp.repository.LivroRepository;
import com.udemy.bibliotecaapp.repository.ReservaRepository;
import com.udemy.bibliotecaapp.repository.UsuarioRepository;
import com.udemy.bibliotecaapp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Reserva save(Reserva reserva) {
        ZonedDateTime dataAtual = ZonedDateTime.now();
        reserva.setDataReserva(dataAtual);
        reserva.setDataDevolucao(dataAtual.plusDays(7));

        Random random = new Random();
        int rnd = random.hashCode();
        reserva.setCodigoInformal(String.valueOf(rnd));

        List<Livro> livros = reserva.getLivros();
        reserva.setUsuario(getUserAuthenticated());
        reserva.setLivros(new ArrayList<>());
        Reserva reservaSalvo = reservaRepository.save(reserva);

        for(Livro livro : livros) {
            Optional<Livro> livroOptional = livroRepository.findById(livro.getId());
            if(livroOptional.isPresent()) {
                livroOptional.get().setReserva(reservaSalvo);
                livroRepository.save(livroOptional.get());
            }
        }
        return reservaSalvo;
    }

    public Usuario getUserAuthenticated() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioRepository.findByLogin(principal.getUsername());
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
