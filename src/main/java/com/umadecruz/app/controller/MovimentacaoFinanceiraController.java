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
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/financeiro")
@RequiredArgsConstructor
public class MovimentacaoFinanceiraController {

    private final MovimentacaoFinanceiraService movimentacaoFinanceiraService;

    @PostMapping("/movimentacoes")
    @PreAuthorize("hasAnyRole('FINANCIAL', 'ADMIN')")
    public ResponseEntity<MovimentacaoResponseDto> salvar(@Valid @RequestBody MovimentacaoRequestDto dto) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.salvar(dto));
    }

    @PutMapping("/movimentacoes/{id}")
    @PreAuthorize("hasAnyRole('FINANCIAL', 'ADMIN')")
    public ResponseEntity<MovimentacaoResponseDto> atualizar(
            @Valid @RequestBody MovimentacaoRequestDto dto, 
            @PathVariable Integer id) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.atualizar(dto, id));
    }

    @GetMapping("/movimentacoes/{id}")
    @PreAuthorize("hasAnyRole('FINANCIAL', 'ADMIN')")
    public ResponseEntity<MovimentacaoResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.buscarPorId(id));
    }

    @GetMapping("/movimentacoes")
    @PreAuthorize("hasAnyRole('FINANCIAL', 'ADMIN')")
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
    @PreAuthorize("hasAnyRole('FINANCIAL', 'ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        movimentacaoFinanceiraService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/relatorios/saldo-atual")
    @PreAuthorize("hasAnyRole('FINANCIAL', 'ADMIN')")
    public ResponseEntity<SaldoAtualDto> consultarSaldoAtual() {
        return ResponseEntity.ok(movimentacaoFinanceiraService.consultarSaldoAtual());
    }

    @GetMapping("/relatorios/resumo")
    @PreAuthorize("hasAnyRole('FINANCIAL', 'ADMIN')")
    public ResponseEntity<ResumoFinanceiroDto> consultarResumo(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.consultarResumo(dataInicio, dataFim));
    }

    @GetMapping("/relatorios/por-categoria")
    @PreAuthorize("hasAnyRole('FINANCIAL', 'ADMIN')")
    public ResponseEntity<List<MovimentacaoPorCategoriaDto>> consultarPorCategoria(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.consultarPorCategoria(dataInicio, dataFim));
    }

    @GetMapping("/relatorios/fluxo-diario")
    @PreAuthorize("hasAnyRole('FINANCIAL', 'ADMIN')")
    public ResponseEntity<List<FluxoCaixaDiarioDto>> consultarFluxoDiario(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.consultarFluxoDiario(dataInicio, dataFim));
    }

    @GetMapping("/relatorios/por-conta")
    @PreAuthorize("hasAnyRole('FINANCIAL', 'ADMIN')")
    public ResponseEntity<List<MovimentacaoPorContaDto>> consultarPorConta(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.consultarPorConta(dataInicio, dataFim));
    }

    @GetMapping("/relatorios/maiores-movimentacoes")
    @PreAuthorize("hasAnyRole('FINANCIAL', 'ADMIN')")
    public ResponseEntity<List<MaiorMovimentacaoDto>> consultarMaioresMovimentacoes(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim,
            @RequestParam(defaultValue = "10") Integer limite) {
        return ResponseEntity.ok(movimentacaoFinanceiraService.consultarMaioresMovimentacoes(dataInicio, dataFim, limite));
    }

}
