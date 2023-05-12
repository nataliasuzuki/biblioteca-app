package com.udemy.bibliotecaapp.service;

import com.udemy.bibliotecaapp.dto.LivroDTO;
import com.udemy.bibliotecaapp.dto.LivroReservadoDTO;
import com.udemy.bibliotecaapp.entity.Livro;
import com.udemy.bibliotecaapp.exception.IdNaoEncontradoException;
import com.udemy.bibliotecaapp.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroDTO> findAll() {
        return livroRepository.findAllLivros();
    }

    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    public Livro update(Livro livro) {
        if(livroRepository.findById(livro.getId()).isEmpty()) {
            throw new IdNaoEncontradoException("ID não encontrado!");
        }
        return livroRepository.save(livro);
    }

    public boolean delete(Long id) {
        if(livroRepository.findById(id).isEmpty()) {
            throw new IdNaoEncontradoException("ID não encontrado!");
        }
        livroRepository.deleteById(id);
        return livroRepository.findById(id).isEmpty();
    }

    public List<LivroReservadoDTO> buscarLivrosReservados() {
        return livroRepository.findLivrosReservados();
    }
}
