package br.com.fiap.softsales.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.softsales.model.Produto;
import br.com.fiap.softsales.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("produto")
@Slf4j
public class ProdutoController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ProdutoRepository repository;

    @GetMapping
    public List<Produto> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Produto create(@RequestBody Produto produto) {
        log.info("cadastrando produto: {}", produto);
        repository.save(produto);
        return produto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> show( @PathVariable Long id){
        log.info("buscando produto com id {}", id);

        var produtoEncontrado = repository.findById(id);

        if(produtoEncontrado.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(produtoEncontrado.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("Apagando produto {}", id);

        var produtoEncontrado = repository.findById(id);

        if(produtoEncontrado.isEmpty())
            return ResponseEntity.notFound().build();

        repository.delete(produtoEncontrado.get());

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(
        @PathVariable Long id,
        @RequestBody Produto produto
    ){
        log.info("Atualizando produto {} para {}", id, produto);
       
        var produtoEncontrado = repository.findById(id);
        if(produtoEncontrado.isEmpty())
            return ResponseEntity.notFound().build();

        var produtoAntigo = produtoEncontrado.get();

        var produtoNovo = new Produto();
        produtoNovo.setId(id);
        produtoNovo.setNome(produto.getNome());
        produtoNovo.setValor(produto.getValor());

        // adicionar produto nova
        repository.save(produtoNovo);

        return ResponseEntity.ok(produtoNovo);
    }
}