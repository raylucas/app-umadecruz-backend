package com.umadecruz.app.repository;

import com.umadecruz.app.enumeration.CategoriaMovimentacao;
import com.umadecruz.app.enumeration.StatusMovimentacao;
import com.umadecruz.app.enumeration.TipoConta;
import com.umadecruz.app.enumeration.TipoMovimentacao;
import com.umadecruz.app.model.MovimentacaoFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimentacaoFinanceiraRepository extends JpaRepository<MovimentacaoFinanceira, Integer> {

    List<MovimentacaoFinanceira> findByDataMovimentacaoBetween(LocalDate inicio, LocalDate fim);

    List<MovimentacaoFinanceira> findByTipoMovimentacaoAndDataMovimentacaoBetween(
            TipoMovimentacao tipo, LocalDate inicio, LocalDate fim);

    List<MovimentacaoFinanceira> findByTipoContaAndDataMovimentacaoBetween(
            TipoConta tipoConta, LocalDate inicio, LocalDate fim);

    List<MovimentacaoFinanceira> findByStatusAndDataMovimentacaoBetween(
            StatusMovimentacao status, LocalDate inicio, LocalDate fim);

    List<MovimentacaoFinanceira> findByCategoriaAndDataMovimentacaoBetween(
            CategoriaMovimentacao categoria, LocalDate inicio, LocalDate fim);

    @Query("SELECT COALESCE(SUM(m.valor), 0) FROM MovimentacaoFinanceira m " +
           "WHERE m.tipoMovimentacao = :tipo AND m.status = :status " +
           "AND m.dataMovimentacao BETWEEN :inicio AND :fim " +
           "AND m.tipoConta = :tipoConta")
    BigDecimal sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetweenAndTipoConta(
            @Param("tipo") TipoMovimentacao tipo,
            @Param("status") StatusMovimentacao status,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim,
            @Param("tipoConta") TipoConta tipoConta);

    @Query("SELECT COALESCE(SUM(m.valor), 0) FROM MovimentacaoFinanceira m " +
           "WHERE m.tipoMovimentacao = :tipo AND m.status = :status " +
           "AND m.dataMovimentacao BETWEEN :inicio AND :fim")
    BigDecimal sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetween(
            @Param("tipo") TipoMovimentacao tipo,
            @Param("status") StatusMovimentacao status,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim);

    @Query("SELECT m FROM MovimentacaoFinanceira m " +
           "WHERE m.status = :status " +
           "AND m.dataMovimentacao BETWEEN :inicio AND :fim " +
           "ORDER BY m.valor DESC")
    List<MovimentacaoFinanceira> findTop10ByStatusAndDataMovimentacaoBetweenOrderByValorDesc(
            @Param("status") StatusMovimentacao status,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim);

    @Query("SELECT m.categoria, " +
           "SUM(CASE WHEN m.tipoMovimentacao = :tipoEntrada THEN m.valor ELSE 0 END) as totalEntradas, " +
           "SUM(CASE WHEN m.tipoMovimentacao = :tipoSaida THEN m.valor ELSE 0 END) as totalSaidas " +
           "FROM MovimentacaoFinanceira m " +
           "WHERE m.status = :status " +
           "AND m.dataMovimentacao BETWEEN :inicio AND :fim " +
           "GROUP BY m.categoria")
    List<Object[]> sumByCategoriaAndDataMovimentacaoBetween(
            @Param("tipoEntrada") TipoMovimentacao tipoEntrada,
            @Param("tipoSaida") TipoMovimentacao tipoSaida,
            @Param("status") StatusMovimentacao status,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim);

}
