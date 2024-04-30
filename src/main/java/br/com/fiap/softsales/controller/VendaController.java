package br.com.fiap.softsales.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.softsales.model.Venda;
import br.com.fiap.softsales.repository.VendaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("venda")
public class VendaController {

    @Autowired
    VendaRepository repository;
    
    @GetMapping
    public List<Venda> index(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Venda create(@RequestBody @Valid Venda venda){
        return repository.save(venda);
    }

}
