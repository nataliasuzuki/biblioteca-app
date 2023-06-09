package com.udemy.bibliotecaapp.service;

import com.udemy.bibliotecaapp.entity.Usuario;
import com.udemy.bibliotecaapp.exception.IdNaoEncontradoException;
import com.udemy.bibliotecaapp.repository.UsuarioRepository;
import com.udemy.bibliotecaapp.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        usuario.setSenha(SecurityConfig.passwordEncoder().encode(usuario.getSenha()));
        usuario.setAtivo(true);
        usuario.setDataCriacao(ZonedDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Usuario usuario) {
        if(usuarioRepository.findById(usuario.getId()).isEmpty()) {
            throw new IdNaoEncontradoException("ID não encontrado!");
        }
        usuario.setSenha(SecurityConfig.passwordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public boolean delete(Long id) {
        if(usuarioRepository.findById(id).isEmpty()) {
            throw new IdNaoEncontradoException("ID não encontrado!");
        }
        usuarioRepository.deleteById(id);
        return usuarioRepository.findById(id).isEmpty();
    }

    public Usuario findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }
}
