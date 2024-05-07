package br.com.fiap.softsales.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.softsales.model.Venda;
import br.com.fiap.softsales.repository.VendaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("venda")
@Slf4j
public class VendaController {

    @Autowired
    VendaRepository repository;
    
    @GetMapping
    public Page<Venda> index(
        @ParameterObject @PageableDefault(size = 5, sort = "data", direction = Direction.DESC) Pageable pageable,
        @RequestParam(required = false) String produtoNome,
        @RequestParam(required = false) Integer mes
    ){
        if (produtoNome != null && mes != null){
            return repository.findByProdutoNomeIgnoreCaseAndDataMonth(produtoNome, mes, pageable);
        }

        if(mes != null){
            return repository.findByDataMonth(mes, pageable);
        }

        if (produtoNome != null){
            return repository.findByProdutoNomeIgnoreCase(produtoNome, pageable);
        }
        return repository.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Venda create(@RequestBody @Valid Venda venda){
        log.info("registrando venda: {}", venda);
        return repository.save(venda);
    }

}
