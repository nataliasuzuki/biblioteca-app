package com.udemy.bibliotecaapp.controller;

import com.udemy.bibliotecaapp.entity.Usuario;
import com.udemy.bibliotecaapp.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity buscarTodosUsuarios() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity criarUsuario(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity alterarUsuario(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.update(usuario), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removerUsuario(@PathVariable Long id) {
        boolean sucesso = usuarioService.delete(id);
        if(sucesso)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
