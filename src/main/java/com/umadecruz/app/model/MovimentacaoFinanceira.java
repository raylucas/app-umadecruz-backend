package com.umadecruz.app.model;

import com.umadecruz.app.enumeration.CategoriaMovimentacao;
import com.umadecruz.app.enumeration.StatusMovimentacao;
import com.umadecruz.app.enumeration.TipoConta;
import com.umadecruz.app.enumeration.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="MOVIMENTACAO_FINANCEIRA")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_MOVIMENTACAO", nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_CONTA", nullable = false)
    private TipoConta tipoConta;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA", nullable = false)
    private CategoriaMovimentacao categoria;

    @Column(name = "DESCRICAO", nullable = false, length = 500)
    private String descricao;

    @Column(name = "VALOR", nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Column(name = "DATA_MOVIMENTACAO", nullable = false)
    private LocalDate dataMovimentacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private StatusMovimentacao status;

    @Column(name = "OBSERVACAO", length = 1000)
    private String observacao;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDate dataAtualizacao;

}
