package com.carlosribeiro.apirestfulv1.repository;

import com.carlosribeiro.apirestfulv1.model.Produto;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByPreco(BigDecimal preco);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Produto p where p.id = :id")
    Optional<Produto> recuperarProdutoPorIdComLock(@Param("id") Long id);

    @Query("select p from Produto p left outer join fetch p.categoria order by p.id")
    List<Produto> recuperarProdutosOrdenadosPorIdProduto();

    @Query("select p from Produto p where p.categoria.id = :id")
    List<Produto> recuperarProdutosPorIdCategoria(@Param("id") long id);

//    @Query(
//            value = "select p from Produto p " +
//                    "left outer join fetch p.categoria " +
//                    "where p.nome like %:nome% " +
//                    "order by p.id desc",
//            countQuery = "select count(p) from Produto p where p.nome like %:nome% "
//    )
//    Page<Produto> recuperarProdutosComPaginacao(Pageable pageable, @Param("nome") String nome);
    @Query(
        value = "select p from Produto p " +
                "left outer join fetch p.categoria " +
                "where p.nome like %:nome% ",
        countQuery = "select count(p) " +
                "from Produto p " +
                "where p.nome like %:nome% "
    )
    Page<Produto> recuperarProdutosComPaginacao(Pageable pageable, @Param("nome") String nome);

    @Query("select p from Produto p " +
            "left outer join fetch p.categoria c " +
            "where c.nome = :nome " +
            "order by p.id")
    List<Produto> recuperarProdutosPorNomeDaCategoria(@Param("nome") String nome);

    @Query(
            value = "select p from Produto p " +
                    "left outer join fetch p.categoria c " +
                    "where c.nome = :nome " +
                    "order by p.nome",
            countQuery = "select count(p) " +
                    "from Produto p " +
                    "left outer join p.categoria c " +
                    "where c.nome = :nome "
    )
    Page<Produto> recuperarProdutosPaginadosPorNomeDaCategoria(@Param("nome") String nome, Pageable pageable);

    @Query(
            value = "select p from Produto p " +
                    "left outer join fetch p.categoria c " +
                    "order by p.nome",
            countQuery = "select count(p) from Produto p "
    )
    Page<Produto> recuperarProdutosPaginados(Pageable pageable);
}
