package br.com.fiap.softsales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.softsales.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

}
