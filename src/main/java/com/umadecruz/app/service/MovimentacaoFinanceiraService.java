package com.umadecruz.app.service;

import com.umadecruz.app.dto.*;
import com.umadecruz.app.enumeration.StatusMovimentacao;
import com.umadecruz.app.enumeration.TipoConta;
import com.umadecruz.app.enumeration.TipoMovimentacao;
import com.umadecruz.app.exception.MovimentacaoNaoEncontradaException;
import com.umadecruz.app.exception.SaldoInsuficienteException;
import com.umadecruz.app.model.MovimentacaoFinanceira;
import com.umadecruz.app.repository.MovimentacaoFinanceiraRepository;
import com.umadecruz.app.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovimentacaoFinanceiraService {

    private final MovimentacaoFinanceiraRepository repository;
    private final ModelMapper modelMapper;

    public MovimentacaoResponseDto salvar(MovimentacaoRequestDto requestDto) {
        validarMovimentacao(requestDto);

        var movimentacao = modelMapper.map(requestDto, MovimentacaoFinanceira.class);
        movimentacao.setDataCriacao(LocalDate.now());

        repository.save(movimentacao);
        return modelMapper.map(movimentacao, MovimentacaoResponseDto.class);
    }

    public MovimentacaoResponseDto atualizar(MovimentacaoRequestDto requestDto, Integer id) {
        validarMovimentacao(requestDto);

        var movimentacao = consultarPorId(id);
        
        if (movimentacao.getStatus() == StatusMovimentacao.CANCELADA) {
            throw new RuntimeException("Não é possível atualizar uma movimentação cancelada");
        }

        modelMapper.map(requestDto, movimentacao);
        movimentacao.setDataAtualizacao(LocalDate.now());

        repository.save(movimentacao);
        return modelMapper.map(movimentacao, MovimentacaoResponseDto.class);
    }

    public MovimentacaoResponseDto buscarPorId(Integer id) {
        var movimentacao = consultarPorId(id);
        return modelMapper.map(movimentacao, MovimentacaoResponseDto.class);
    }

    public List<MovimentacaoResponseDto> buscarTodos(
            TipoMovimentacao tipo,
            TipoConta tipoConta,
            String categoria,
            StatusMovimentacao status,
            LocalDate dataInicio,
            LocalDate dataFim) {

        List<MovimentacaoFinanceira> movimentacoes;

        if (dataInicio != null && dataFim != null) {
            if (tipo != null) {
                movimentacoes = repository.findByTipoMovimentacaoAndDataMovimentacaoBetween(tipo, dataInicio, dataFim);
            } else if (tipoConta != null) {
                movimentacoes = repository.findByTipoContaAndDataMovimentacaoBetween(tipoConta, dataInicio, dataFim);
            } else if (status != null) {
                movimentacoes = repository.findByStatusAndDataMovimentacaoBetween(status, dataInicio, dataFim);
            } else {
                movimentacoes = repository.findByDataMovimentacaoBetween(dataInicio, dataFim);
            }
        } else {
            movimentacoes = repository.findAll();
        }

        if (categoria != null) {
            try {
                var categoriaEnum = com.umadecruz.app.enumeration.CategoriaMovimentacao.valueOf(categoria.toUpperCase());
                movimentacoes = movimentacoes.stream()
                        .filter(m -> m.getCategoria() == categoriaEnum)
                        .collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                return List.of();
            }
        }

        return MapperUtil.mapList(movimentacoes, MovimentacaoResponseDto.class, modelMapper);
    }

    public void excluir(Integer id) {
        var movimentacao = consultarPorId(id);
        
        if (movimentacao.getStatus() == StatusMovimentacao.CANCELADA) {
            throw new RuntimeException("Movimentação já está cancelada");
        }

        movimentacao.setStatus(StatusMovimentacao.CANCELADA);
        movimentacao.setDataAtualizacao(LocalDate.now());
        
        repository.save(movimentacao);
    }

    public SaldoAtualDto consultarSaldoAtual() {
        LocalDate hoje = LocalDate.now();
        LocalDate inicio = LocalDate.of(1900, 1, 1);

        BigDecimal entradasContaDigital = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetweenAndTipoConta(
                TipoMovimentacao.ENTRADA, StatusMovimentacao.CONFIRMADA, inicio, hoje, TipoConta.CONTA_DIGITAL);
        
        BigDecimal saidasContaDigital = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetweenAndTipoConta(
                TipoMovimentacao.SAIDA, StatusMovimentacao.CONFIRMADA, inicio, hoje, TipoConta.CONTA_DIGITAL);
        
        BigDecimal entradasDinheiroFisico = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetweenAndTipoConta(
                TipoMovimentacao.ENTRADA, StatusMovimentacao.CONFIRMADA, inicio, hoje, TipoConta.DINHEIRO_FISICO);
        
        BigDecimal saidasDinheiroFisico = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetweenAndTipoConta(
                TipoMovimentacao.SAIDA, StatusMovimentacao.CONFIRMADA, inicio, hoje, TipoConta.DINHEIRO_FISICO);

        BigDecimal saldoContaDigital = entradasContaDigital.subtract(saidasContaDigital);
        BigDecimal saldoDinheiroFisico = entradasDinheiroFisico.subtract(saidasDinheiroFisico);
        BigDecimal saldoTotal = saldoContaDigital.add(saldoDinheiroFisico);

        return SaldoAtualDto.builder()
                .saldoTotal(saldoTotal)
                .saldoContaDigital(saldoContaDigital)
                .saldoDinheiroFisico(saldoDinheiroFisico)
                .build();
    }

    public ResumoFinanceiroDto consultarResumo(LocalDate dataInicio, LocalDate dataFim) {
        BigDecimal totalEntradas = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetween(
                TipoMovimentacao.ENTRADA, StatusMovimentacao.CONFIRMADA, dataInicio, dataFim);
        
        BigDecimal totalSaidas = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetween(
                TipoMovimentacao.SAIDA, StatusMovimentacao.CONFIRMADA, dataInicio, dataFim);
        
        BigDecimal saldoLiquido = totalEntradas.subtract(totalSaidas);

        return ResumoFinanceiroDto.builder()
                .totalEntradas(totalEntradas)
                .totalSaidas(totalSaidas)
                .saldoLiquido(saldoLiquido)
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .build();
    }

    public List<MovimentacaoPorCategoriaDto> consultarPorCategoria(LocalDate dataInicio, LocalDate dataFim) {
        List<Object[]> results = repository.sumByCategoriaAndDataMovimentacaoBetween(
                TipoMovimentacao.ENTRADA, TipoMovimentacao.SAIDA, StatusMovimentacao.CONFIRMADA, dataInicio, dataFim);
        
        return results.stream()
                .map(result -> MovimentacaoPorCategoriaDto.builder()
                        .categoria((com.umadecruz.app.enumeration.CategoriaMovimentacao) result[0])
                        .totalEntradas((BigDecimal) result[1])
                        .totalSaidas((BigDecimal) result[2])
                        .saldo(((BigDecimal) result[1]).subtract((BigDecimal) result[2]))
                        .build())
                .collect(Collectors.toList());
    }

    public List<FluxoCaixaDiarioDto> consultarFluxoDiario(LocalDate dataInicio, LocalDate dataFim) {
        LocalDate dataAtual = dataInicio;
        BigDecimal saldoAcumulado = BigDecimal.ZERO;

        List<FluxoCaixaDiarioDto> fluxoDiario = new ArrayList<>();

        while (!dataAtual.isAfter(dataFim)) {
            BigDecimal entradasDoDia = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetween(
                    TipoMovimentacao.ENTRADA, StatusMovimentacao.CONFIRMADA, dataAtual, dataAtual);
            
            BigDecimal saidasDoDia = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetween(
                    TipoMovimentacao.SAIDA, StatusMovimentacao.CONFIRMADA, dataAtual, dataAtual);
            
            BigDecimal saldoDoDia = entradasDoDia.subtract(saidasDoDia);
            saldoAcumulado = saldoAcumulado.add(saldoDoDia);

            fluxoDiario.add(FluxoCaixaDiarioDto.builder()
                    .data(dataAtual)
                    .entradasDoDia(entradasDoDia)
                    .saidasDoDia(saidasDoDia)
                    .saldoDoDia(saldoDoDia)
                    .saldoAcumulado(saldoAcumulado)
                    .build());

            dataAtual = dataAtual.plusDays(1);
        }

        return fluxoDiario;
    }

    public List<MovimentacaoPorContaDto> consultarPorConta(LocalDate dataInicio, LocalDate dataFim) {
        LocalDate inicio = LocalDate.of(1900, 1, 1);

        BigDecimal entradasContaDigital = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetweenAndTipoConta(
                TipoMovimentacao.ENTRADA, StatusMovimentacao.CONFIRMADA, inicio, dataFim, TipoConta.CONTA_DIGITAL);
        
        BigDecimal saidasContaDigital = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetweenAndTipoConta(
                TipoMovimentacao.SAIDA, StatusMovimentacao.CONFIRMADA, inicio, dataFim, TipoConta.CONTA_DIGITAL);
        
        BigDecimal entradasDinheiroFisico = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetweenAndTipoConta(
                TipoMovimentacao.ENTRADA, StatusMovimentacao.CONFIRMADA, inicio, dataFim, TipoConta.DINHEIRO_FISICO);
        
        BigDecimal saidasDinheiroFisico = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetweenAndTipoConta(
                TipoMovimentacao.SAIDA, StatusMovimentacao.CONFIRMADA, inicio, dataFim, TipoConta.DINHEIRO_FISICO);

        BigDecimal saldoAtualContaDigital = entradasContaDigital.subtract(saidasContaDigital);
        BigDecimal saldoAtualDinheiroFisico = entradasDinheiroFisico.subtract(saidasDinheiroFisico);

        return List.of(
                MovimentacaoPorContaDto.builder()
                        .tipoConta(TipoConta.CONTA_DIGITAL)
                        .totalEntradas(entradasContaDigital)
                        .totalSaidas(saidasContaDigital)
                        .saldoAtual(saldoAtualContaDigital)
                        .build(),
                MovimentacaoPorContaDto.builder()
                        .tipoConta(TipoConta.DINHEIRO_FISICO)
                        .totalEntradas(entradasDinheiroFisico)
                        .totalSaidas(saidasDinheiroFisico)
                        .saldoAtual(saldoAtualDinheiroFisico)
                        .build()
        );
    }

    public List<MaiorMovimentacaoDto> consultarMaioresMovimentacoes(LocalDate dataInicio, LocalDate dataFim, Integer limite) {
        List<MovimentacaoFinanceira> movimentacoes = repository.findTop10ByStatusAndDataMovimentacaoBetweenOrderByValorDesc(
                StatusMovimentacao.CONFIRMADA, dataInicio, dataFim);
        
        int limit = limite != null ? limite : 10;
        AtomicInteger index = new AtomicInteger(1);
        
        return movimentacoes.stream()
                .limit(limit)
                .map(movimentacao -> MaiorMovimentacaoDto.builder()
                        .id(movimentacao.getId())
                        .descricao(movimentacao.getDescricao())
                        .valor(movimentacao.getValor())
                        .tipoMovimentacao(movimentacao.getTipoMovimentacao())
                        .tipoConta(movimentacao.getTipoConta())
                        .categoria(movimentacao.getCategoria())
                        .dataMovimentacao(movimentacao.getDataMovimentacao())
                        .status(movimentacao.getStatus())
                        .ranking(index.getAndIncrement())
                        .build())
                .collect(Collectors.toList());
    }

    private void validarMovimentacao(MovimentacaoRequestDto requestDto) {
        if (requestDto.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor deve ser maior que zero");
        }

        if (requestDto.getDataMovimentacao().isAfter(LocalDate.now())) {
            throw new RuntimeException("A data da movimentação não pode ser futura");
        }

        if (requestDto.getDescricao() == null || requestDto.getDescricao().trim().isEmpty()) {
            throw new RuntimeException("A descrição é obrigatória");
        }

        if (requestDto.getTipoMovimentacao() == TipoMovimentacao.SAIDA) {
            validarSaldoSuficiente(requestDto);
        }
    }

    private void validarSaldoSuficiente(MovimentacaoRequestDto requestDto) {
        LocalDate hoje = LocalDate.now();
        LocalDate inicio = LocalDate.of(1900, 1, 1);

        BigDecimal entradas = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetweenAndTipoConta(
                TipoMovimentacao.ENTRADA, StatusMovimentacao.CONFIRMADA, inicio, hoje, requestDto.getTipoConta());
        
        BigDecimal saidas = repository.sumByTipoMovimentacaoAndStatusAndDataMovimentacaoBetweenAndTipoConta(
                TipoMovimentacao.SAIDA, StatusMovimentacao.CONFIRMADA, inicio, hoje, requestDto.getTipoConta());
        
        BigDecimal saldoAtual = entradas.subtract(saidas);

        if (saldoAtual.compareTo(requestDto.getValor()) < 0) {
            String tipoContaDescricao = requestDto.getTipoConta() == TipoConta.CONTA_DIGITAL ? "conta digital" : "dinheiro físico";
            throw new SaldoInsuficienteException(
                    String.format("Saldo insuficiente na %s. Saldo atual: R$ %.2f, Tentativa de saída: R$ %.2f",
                            tipoContaDescricao, saldoAtual, requestDto.getValor()));
        }
    }

    private MovimentacaoFinanceira consultarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new MovimentacaoNaoEncontradaException());
    }
}
