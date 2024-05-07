package br.com.fiap.softsales.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.softsales.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    // @Query("SELECT m FROM Venda m WHERE m.produto.nome = :produtoNome ORDER BY data DESC LIMIT 1 GROUP BY produto ") // JPQL
    Page<Venda> findByProdutoNomeIgnoreCase(String produtoNome, Pageable pageable);

    @Query("SELECT m FROM Venda m WHERE m.produto.nome = :produtoNome AND MONTH(m.data) = :mes")
    Page<Venda> findByProdutoNomeIgnoreCaseAndDataMonth(String produtoNome, int mes, Pageable pageable);

    @Query("SELECT m FROM Venda m WHERE MONTH(m.data) = :mes")
    Page<Venda> findByDataMonth(Integer mes, Pageable pageable);

}
