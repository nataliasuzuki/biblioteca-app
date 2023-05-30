package com.udemy.bibliotecaapp.controller;

import com.udemy.bibliotecaapp.dto.LoginDTO;
import com.udemy.bibliotecaapp.dto.TokenDTO;
import com.udemy.bibliotecaapp.security.CustomUserDetailService;
import com.udemy.bibliotecaapp.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtService jwtService;

    @Value("${security.jwt.expiration}")
    private String expiracao;

    @PostMapping
    public ResponseEntity post(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            customUserDetailService.validaCredenciais(loginDTO);
            String token = jwtService.geraToken(loginDTO.getLogin());
            return new ResponseEntity<>(new TokenDTO(token, expiracao), HttpStatus.OK);
        } catch (Exception erro) {
            return new ResponseEntity<>(erro.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
