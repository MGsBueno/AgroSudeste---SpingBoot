package com.carlosribeiro.apirestfulv1.service;

import com.carlosribeiro.apirestfulv1.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.apirestfulv1.model.Categoria;
import com.carlosribeiro.apirestfulv1.model.CategoriaDTO;
import com.carlosribeiro.apirestfulv1.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    public CategoriaDTO recuperarCategoriaComProdutosPorIdCategoria(long id) {
//        Categoria umaCategoria = categoriaRepository.findById(id)
//                .orElseThrow(() -> new EntidadeNaoEncontradaException(
//                        "Categoria número " + id + " não encontrada."));
        Categoria umaCategoria = categoriaRepository.recuperarCategoriaComProdutosPorIdCategoria(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Categoria número " + id + " não encontrada."));
        return new CategoriaDTO(umaCategoria.getNome(), umaCategoria.getProdutos());
    }
}
