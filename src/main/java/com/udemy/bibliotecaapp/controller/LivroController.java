package com.udemy.bibliotecaapp.controller;

import com.udemy.bibliotecaapp.dto.LivroReservadoDTO;
import com.udemy.bibliotecaapp.entity.Livro;
import com.udemy.bibliotecaapp.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity buscarTodosLivros() {
        return new ResponseEntity<>(livroService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/reservados")
    public ResponseEntity buscarLivrosReservados() {
        List<LivroReservadoDTO> livros = livroService.buscarLivrosReservados();
        if(!livros.isEmpty())
            return new ResponseEntity<>(livros, HttpStatus.OK);
        else
            return new ResponseEntity<>("Lista vazia!", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity criarLivro(@Valid @RequestBody Livro livro) {
        return new ResponseEntity<>(livroService.save(livro), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity alterarLivro(@Valid @RequestBody Livro livro) {
        return new ResponseEntity<>(livroService.update(livro), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removerLivro(@PathVariable Long id) {
        boolean sucesso = livroService.delete(id);
        if(sucesso)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity devolverLivro(@PathVariable Long id) {
        boolean sucesso = livroService.devolverLivro(id);
        if(sucesso)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
