package com.carlosribeiro.apirestfulv1.model;

import java.util.List;

public record CategoriaDTO(String nome, List<Produto> produtos) {

}
