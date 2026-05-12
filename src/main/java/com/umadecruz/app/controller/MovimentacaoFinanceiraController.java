package com.umadecruz.app.controller;

import com.umadecruz.app.dto.*;
import com.umadecruz.app.enumeration.StatusMovimentacao;
import com.umadecruz.app.enumeration.TipoConta;
import com.umadecruz.app.enumeration.TipoMovimentacao;
import com.umadecruz.app.service.MovimentacaoFinanceiraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/financeiro")
@RequiredArgsConstructor
public class MovimentacaoFinanceiraController {

    private final MovimentacaoFinanceiraService movimentacaoFinanceiraService;

    @PostMapping("/movimentacoes")
    public ResponseEntity<MovimentacaoResponseDto> salvar(@Valid @RequestBody MovimentacaoRequestDto dto) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.salvar(dto));
    }

    @PutMapping("/movimentacoes/{id}")
    public ResponseEntity<MovimentacaoResponseDto> atualizar(
            @Valid @RequestBody MovimentacaoRequestDto dto, 
            @PathVariable Integer id) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.atualizar(dto, id));
    }

    @GetMapping("/movimentacoes/{id}")
    public ResponseEntity<MovimentacaoResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.buscarPorId(id));
    }

    @GetMapping("/movimentacoes")
    public ResponseEntity<List<MovimentacaoResponseDto>> buscarTodos(
            @RequestParam(required = false) TipoMovimentacao tipo,
            @RequestParam(required = false) TipoConta tipoConta,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) StatusMovimentacao status,
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.buscarTodos(
                tipo, tipoConta, categoria, status, dataInicio, dataFim));
    }

    @DeleteMapping("/movimentacoes/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        try {
            movimentacaoFinanceiraService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/relatorios/saldo-atual")
    public ResponseEntity<SaldoAtualDto> consultarSaldoAtual() {
        return ResponseEntity.ok(movimentacaoFinanceiraService.consultarSaldoAtual());
    }

    @GetMapping("/relatorios/resumo")
    public ResponseEntity<ResumoFinanceiroDto> consultarResumo(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.consultarResumo(dataInicio, dataFim));
    }

    @GetMapping("/relatorios/por-categoria")
    public ResponseEntity<List<MovimentacaoPorCategoriaDto>> consultarPorCategoria(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.consultarPorCategoria(dataInicio, dataFim));
    }

    @GetMapping("/relatorios/fluxo-diario")
    public ResponseEntity<List<FluxoCaixaDiarioDto>> consultarFluxoDiario(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.consultarFluxoDiario(dataInicio, dataFim));
    }

    @GetMapping("/relatorios/por-conta")
    public ResponseEntity<List<MovimentacaoPorContaDto>> consultarPorConta(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.consultarPorConta(dataInicio, dataFim));
    }

    @GetMapping("/relatorios/maiores-movimentacoes")
    public ResponseEntity<List<MaiorMovimentacaoDto>> consultarMaioresMovimentacoes(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim,
            @RequestParam(defaultValue = "10") Integer limite) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.consultarMaioresMovimentacoes(dataInicio, dataFim, limite));
    }

}
