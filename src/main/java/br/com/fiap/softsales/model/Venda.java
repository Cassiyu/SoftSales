package br.com.fiap.softsales.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.fiap.softsales.validation.TipoVenda;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Venda{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Positive(message = "{venda.valor.positive}")
    private BigDecimal valor;

    private LocalDate data;

    @TipoVenda
    private String tipo;

    @ManyToOne
    private Produto produto;

}