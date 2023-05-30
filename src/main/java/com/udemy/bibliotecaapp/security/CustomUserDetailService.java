package com.udemy.bibliotecaapp.security;

import com.udemy.bibliotecaapp.dto.LoginDTO;
import com.udemy.bibliotecaapp.entity.Usuario;
import com.udemy.bibliotecaapp.exception.IdNaoEncontradoException;
import com.udemy.bibliotecaapp.exception.SenhaInvalidaException;
import com.udemy.bibliotecaapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByLogin(login);
        if(usuario == null) {
            throw new IdNaoEncontradoException("Usuário não encontrado.");
        }
        return new UserPrincipal(usuario);
    }

    public void validaCredenciais(LoginDTO loginDTO) {
        UserDetails userDetails = loadUserByUsername(loginDTO.getLogin());
        boolean senhaValida = SecurityConfig.passwordEncoder().matches(loginDTO.getSenha(), userDetails.getPassword());
        if(!senhaValida) {
            throw new SenhaInvalidaException("Senha inválida.");
        }
    }
}
