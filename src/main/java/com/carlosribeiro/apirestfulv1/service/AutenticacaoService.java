package com.carlosribeiro.apirestfulv1.service;

import com.carlosribeiro.apirestfulv1.model.Usuario;
import com.carlosribeiro.apirestfulv1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario login(Usuario usuario) {
        System.out.println("Conta = " + usuario.getConta() + " e senha = " + usuario.getSenha());
        return usuarioRepository.findByContaAndSenha(
                usuario.getConta(), usuario.getSenha());
    }
    public Usuario save(Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findByContaAndSenha(
                usuario.getConta(), usuario.getSenha());

        if (usuarioExistente != null) {
            return null; // Retorna null se o usuário já existir
        } else {
            usuarioRepository.save(usuario);
            return usuarioRepository.findByContaAndSenha( // Retorna o usuário já cadastrado (para login automático)
                    usuario.getConta(), usuario.getSenha());
        }
    }
}
