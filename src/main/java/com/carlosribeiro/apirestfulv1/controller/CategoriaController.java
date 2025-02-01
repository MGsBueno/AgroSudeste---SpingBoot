package com.carlosribeiro.apirestfulv1.controller;

import com.carlosribeiro.apirestfulv1.model.Categoria;
import com.carlosribeiro.apirestfulv1.model.CategoriaDTO;
import com.carlosribeiro.apirestfulv1.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("categorias")   // http://localhost:8080/categorias
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping ("{idCategoria}/produtos")   // http://localhost:8080/categorias/1/produtos
    public CategoriaDTO recuperarCategoriaComProdutosPorIdCategoria(@PathVariable("idCategoria") long id) {
        return categoriaService.recuperarCategoriaComProdutosPorIdCategoria(id);
    }
}
