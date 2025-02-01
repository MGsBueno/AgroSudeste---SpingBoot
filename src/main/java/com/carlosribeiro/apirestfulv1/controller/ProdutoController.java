package com.carlosribeiro.apirestfulv1.controller;

import com.carlosribeiro.apirestfulv1.model.Produto;
import com.carlosribeiro.apirestfulv1.model.ResultadoPaginado;
import com.carlosribeiro.apirestfulv1.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("produtos")    // http://localhost:8080/produtos
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping    // GET para http://localhost:8080/produtos
    public ResponseEntity<List<Produto>> recuperarProdutos() {
        return new ResponseEntity<>(produtoService.recuperarProdutos(), HttpStatus.OK);
    }

    @GetMapping("categoria/{nomeCategoria}")    // GET para http://localhost:8080/produtos/categoria/1
    public List<Produto> recuperarProdutosPorNomeDaCategoria(@PathVariable("nomeCategoria") String nome) {
        return produtoService.recuperarProdutosPorNomeDaCategoria(nome);
    }

    @GetMapping("{idProduto}")    // GET para http://localhost:8080/produtos/1
    public Produto recuperarProduto(@PathVariable("idProduto") long id) {
        return produtoService.recuperarProdutoPorId(id);
    }

//    @GetMapping    // GET para http://localhost:8080/produtos
//    public List<Produto> recuperarProdutos() {
//        return produtoService.recuperarProdutos();
//    }

    @PostMapping
    public Produto cadastrarProduto(@RequestBody Produto produto) {
        return produtoService.cadastrarProduto(produto);
    }

    @PutMapping
    public Produto alterarProduto(@RequestBody Produto produto) {
        return produtoService.alterarProduto(produto);
    }

    // Requisição do tipo DELETE para http://localhost:8080/produto/2
    @DeleteMapping("{idProduto}")
    public void removerProduto(@PathVariable("idProduto") long id) {
        produtoService.removerProduto(id);
    }

//    // http://localhost:8080/produtos/paginacao?pagina=0&tamanho=5
//    @GetMapping("paginacao")
//    public ResultadoPaginado<Produto> recuperarProdutosComPaginacao(
//            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
//            @RequestParam(value = "tamanho", defaultValue = "3") int tamanho,
//            @RequestParam(value = "nome", defaultValue = "") String nome) {
//        Pageable pageable = PageRequest.of(pagina, tamanho);
//        Page<Produto> page = produtoService.recuperarProdutosComPaginacao(pageable, nome);
//        return new ResultadoPaginado<>(
//                page.getTotalElements(),
//                page.getTotalPages(),
//                page.getNumber(),
//                page.getContent());
//    }

    // http://localhost:8080/produtos/paginacao?pagina=0&tamanho=5
    @GetMapping("paginacao")
    public ResultadoPaginado<Produto> recuperarProdutosComPaginacao(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "3") int tamanho,
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "campo", defaultValue = "id") String campo,
            @RequestParam(value = "ordem", defaultValue = "desc") String ordem) {
        Sort sort;
        if (ordem.equals("asc")) {
            sort = Sort.by(Sort.Direction.ASC, campo);
        }
        else {
            sort = Sort.by(Sort.Direction.DESC, campo);
        }
        Pageable pageable = PageRequest.of(pagina, tamanho, sort);
        Page<Produto> page = produtoService.recuperarProdutosComPaginacao(pageable, nome);
        ResultadoPaginado<Produto> resultadoPaginado = new ResultadoPaginado<>(
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getContent());
        return resultadoPaginado;
    }

    @GetMapping("categoria/paginacao")
    public ResultadoPaginado<Produto> recuperarProdutosPaginadosPorNomeDaCategoria(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "3") int tamanho,
            @RequestParam(value = "nomeCategoria", defaultValue = "") String nomeCategoria) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Produto> page = produtoService.recuperarProdutosPaginadosPorNomeDaCategoria(nomeCategoria, pageable);
        ResultadoPaginado<Produto> resultadoPaginado = new ResultadoPaginado<>(
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getContent());
        return resultadoPaginado;
    }
}
