package br.com.fiap.softsales.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.softsales.model.Produto;
import br.com.fiap.softsales.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("produto")
@Slf4j
@CacheConfig(cacheNames = "produtos")
@Tag(name = "produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @GetMapping
    @Cacheable
    @Operation(
        summary = "Listar Produtos",
        description = "Retorna um array com os produtos do usuário autenticado."
    )
    public List<Produto> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique o corpo da requisição")
    })
    @CacheEvict(allEntries = true)  
    public Produto create(@RequestBody @Valid Produto produto) {
        log.info("cadastrando produto: {}", produto);
        return repository.save(produto);
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> show( @PathVariable Long id){
        log.info("buscando produto com id {}", id);

        return repository
                    .findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build()); 
    }   

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando produto {}", id);
        verificarSeExisteProduto(id);
        repository.deleteById(id);
    }


    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    public Produto update(@PathVariable Long id, @RequestBody Produto produto){
        log.info("atualizando produto com id {} para {}", id, produto);

        verificarSeExisteProduto(id);
        produto.setId(id);
        return repository.save(produto);
    }

    private void verificarSeExisteProduto(Long id){
        repository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                                    NOT_FOUND,
                                    "id do produto não encontrado"
                                    ));
    }
}