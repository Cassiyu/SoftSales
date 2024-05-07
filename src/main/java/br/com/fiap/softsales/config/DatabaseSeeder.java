package br.com.fiap.softsales.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.softsales.model.Produto;
import br.com.fiap.softsales.model.Venda;
import br.com.fiap.softsales.repository.ProdutoRepository;
import br.com.fiap.softsales.repository.VendaRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    VendaRepository vendaRepository;

    @Override
    public void run(String... args) throws Exception {

        produtoRepository.saveAll(
                List.of(
                        Produto.builder().id(1L).nome("Produto A").valor(new BigDecimal("10")).build(),
                        Produto.builder().id(2L).nome("Produto B").valor(new BigDecimal("20")).build(),
                        Produto.builder().id(3L).nome("Produto C").valor(new BigDecimal("30")).build(),
                        Produto.builder().id(4L).nome("Produto D").valor(new BigDecimal("40")).build()));

        vendaRepository.saveAll(
                List.of(
                        Venda.builder()
                                .id(1L)                             
                                .data(LocalDate.now())
                                .valor(new BigDecimal(100))
                                .tipo("RECEITA")
                                .produto(produtoRepository.findById(1L).get())
                                .build(),
                        Venda.builder()
                                .id(2L)       
                                .data(LocalDate.now().minusDays(1))
                                .valor(new BigDecimal(80))
                                .tipo("RECEITA")
                                .produto(produtoRepository.findById(2L).get())
                                .build(),
                        Venda.builder()
                                .id(3L)                              
                                .data(LocalDate.now())
                                .valor(new BigDecimal(90))
                                .tipo("RECEITA")
                                .produto(produtoRepository.findById(3L).get())
                                .build(),
                        Venda.builder()
                                .id(4L)
                                .data(LocalDate.now())
                                .valor(new BigDecimal(1000))
                                .tipo("DESPESA")
                                .produto(produtoRepository.findById(4L).get())
                                .build(),
                        Venda.builder()
                                .id(5L)
                                .data(LocalDate.now())
                                .valor(new BigDecimal(1000))
                                .tipo("DESPESA")
                                .produto(produtoRepository.findById(5L).get())
                                .build()
                       
                )
        );
    }

}
