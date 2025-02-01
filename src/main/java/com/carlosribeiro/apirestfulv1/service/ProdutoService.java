package com.carlosribeiro.apirestfulv1.service;

import com.carlosribeiro.apirestfulv1.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.apirestfulv1.model.Produto;
import com.carlosribeiro.apirestfulv1.repository.CategoriaRepository;
import com.carlosribeiro.apirestfulv1.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Produto> recuperarProdutos() {
        return produtoRepository.recuperarProdutosOrdenadosPorIdProduto();
    }

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto alterarProduto(Produto produto) {
        produtoRepository.recuperarProdutoPorIdComLock(produto.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Produto número " + produto.getId() + " não encontrado."));
        return produtoRepository.save(produto);
    }

    public void removerProduto(long id) {
        recuperarProdutoPorId(id);
        produtoRepository.deleteById(id);
    }

    public Produto recuperarProdutoPorId(long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Produto número " + id + " não encontrado."));
    }

    public List<Produto> recuperarProdutosPorIdCategoria(long id) {
        categoriaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Categoria número " + id + " não encontrada."));
        return produtoRepository.recuperarProdutosPorIdCategoria(id);
    }

    public Page<Produto> recuperarProdutosComPaginacao(Pageable pageable, String nome) {
        return produtoRepository.recuperarProdutosComPaginacao(pageable, nome);
    }

    public List<Produto> recuperarProdutosPorNomeDaCategoria(String nome) {
        return produtoRepository.recuperarProdutosPorNomeDaCategoria(nome);
    }

    public Page<Produto> recuperarProdutosPaginadosPorNomeDaCategoria(String nome, Pageable pageable) {
        if(!nome.isEmpty()) {
            return produtoRepository.recuperarProdutosPaginadosPorNomeDaCategoria(nome, pageable);
        }
        else {
            return produtoRepository.recuperarProdutosPaginados(pageable);
        }
    }
}
