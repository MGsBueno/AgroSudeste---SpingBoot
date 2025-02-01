package com.carlosribeiro.apirestfulv1;

import com.carlosribeiro.apirestfulv1.model.Categoria;
import com.carlosribeiro.apirestfulv1.model.Produto;
import com.carlosribeiro.apirestfulv1.repository.CategoriaRepository;
import com.carlosribeiro.apirestfulv1.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Apirestfulv1Application implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public Apirestfulv1Application(ProdutoRepository produtoRepository,
                                   CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Apirestfulv1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Categories
        Categoria maquinario = categoriaRepository.save(new Categoria("Maquinarios"));
        Categoria implemento = categoriaRepository.save(new Categoria("Implementos"));
        Categoria insumo = categoriaRepository.save(new Categoria("Insumos"));

        // Sample images
        List<String> imagensMaquinario = List.of(
            "trator.jpg", "colheitadeira.jpg", "plantadeira.jpg", "arado.jpg"
        );
        List<String> imagensImplemento = List.of(
            "semeadora.jpg", "carreta.jpg", "roçadeira.jpg", "pulverizador.jpg"
        );
        List<String> imagensInsumo = List.of(
            "adubo.jpg", "sementes.jpg", "defensivo.jpg", "fertilizante.jpg"
        );

        // Random generator for product details
        Random random = new Random();

        // Generate 40 products: 15 Maquinários, 15 Implementos, 10 Insumos
        for (int i = 1; i <= 15; i++) {
            String imagem = imagensMaquinario.get(random.nextInt(imagensMaquinario.size()));  // Random image for Maquinários
            String nome = "Máquina " + i;
            String descricao = "Máquina para uso agrícola " + i;
            BigDecimal preco = BigDecimal.valueOf(random.nextInt(500000) + 10000);  // Random price between 10,000 and 500,000
            int estoque = random.nextInt(50) + 1;  // Random stock between 1 and 50
            LocalDate dataCadastro = LocalDate.of(2024, random.nextInt(12) + 1, random.nextInt(28) + 1);  // Random date in 2024

            // Create and save the product in Maquinário category
            Produto produto = new Produto(
                imagem, 
                nome, 
                descricao, 
                true, 
                estoque, 
                preco, 
                dataCadastro, 
                maquinario
            );
            produtoRepository.save(produto);
        }

        for (int i = 1; i <= 15; i++) {
            String imagem = imagensImplemento.get(random.nextInt(imagensImplemento.size()));  // Random image for Implementos
            String nome = "Implemento " + i;
            String descricao = "Implemento para máquinas agrícolas " + i;
            BigDecimal preco = BigDecimal.valueOf(random.nextInt(200000) + 5000);  // Random price between 5,000 and 200,000
            int estoque = random.nextInt(100) + 1;  // Random stock between 1 and 100
            LocalDate dataCadastro = LocalDate.of(2024, random.nextInt(12) + 1, random.nextInt(28) + 1);  // Random date in 2024

            // Create and save the product in Implemento category
            Produto produto = new Produto(
                imagem, 
                nome, 
                descricao, 
                true, 
                estoque, 
                preco, 
                dataCadastro, 
                implemento
            );
            produtoRepository.save(produto);
        }

        for (int i = 1; i <= 10; i++) {
            String imagem = imagensInsumo.get(random.nextInt(imagensInsumo.size()));  // Random image for Insumos
            String nome = "Insumo " + i;
            String descricao = "Insumo para cultivo " + i;
            BigDecimal preco = BigDecimal.valueOf(random.nextInt(5000) + 50);  // Random price between 50 and 5,000
            int estoque = random.nextInt(2000) + 1;  // Random stock between 1 and 2000
            LocalDate dataCadastro = LocalDate.of(2024, random.nextInt(12) + 1, random.nextInt(28) + 1);  // Random date in 2024

            // Create and save the product in Insumo category
            Produto produto = new Produto(
                imagem, 
                nome, 
                descricao, 
                true, 
                estoque, 
                preco, 
                dataCadastro, 
                insumo
            );
            produtoRepository.save(produto);
        }
    }
}
