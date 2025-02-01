package com.carlosribeiro.apirestfulv1.repository;

import com.carlosribeiro.apirestfulv1.model.Categoria;
import com.carlosribeiro.apirestfulv1.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select c from Categoria c left outer join fetch c.produtos where c.id = :id")
    Optional<Categoria> recuperarCategoriaComProdutosPorIdCategoria(@Param("id") long id);
}
