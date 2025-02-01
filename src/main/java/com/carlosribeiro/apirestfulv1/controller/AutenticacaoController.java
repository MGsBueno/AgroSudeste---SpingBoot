package com.carlosribeiro.apirestfulv1.controller;

import com.carlosribeiro.apirestfulv1.model.Usuario;
import com.carlosribeiro.apirestfulv1.service.AutenticacaoService;
import com.carlosribeiro.apirestfulv1.util.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("autenticacao")   // http://localhost:8080/autenticacao
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("login")  // http://localhost:8080/autenticacao/login
    public TokenResponse login(@RequestBody Usuario usuario) {
        Usuario usuarioLogado = autenticacaoService.login(usuario);
        if (usuarioLogado != null) {
            return new TokenResponse(usuarioLogado.getConta());
        } else {
            return new TokenResponse("");
        }
    }

    @PostMapping("cadastro") // http://localhost:8080/autenticacao/cadastro
    public ResponseEntity<TokenResponse> cadastrar(@RequestBody Usuario usuario) {
        Usuario usuarioCriado = autenticacaoService.save(usuario);

        if (usuarioCriado != null) {
            return ResponseEntity.ok(new TokenResponse(usuarioCriado.getConta())); // Retorna o token para login automático
        } else {
            return ResponseEntity.badRequest().body(new TokenResponse("")); // Retorna erro se o usuário já existir
        }
    }

}
