package com.umadecruz.app.controller;

import com.umadecruz.app.dto.*;
import com.umadecruz.app.service.UsuarioEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario-evento")
@RequiredArgsConstructor
public class UsuarioEventoController {

    private final UsuarioEventoService usuarioEventoService;

    @PostMapping("/presenca")
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<UsuarioEventoDto> registrarPresenca(@RequestBody UsuarioEventoPresencaDto dto) {
        return ResponseEntity.ok(usuarioEventoService.salvar(dto));
    }

    @GetMapping("/presenca/{idEvento}/{idUsuario}")
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<UsuarioEventoDto> consultarPresenca(
            @PathVariable Integer idEvento, 
            @PathVariable Integer idUsuario) {
        return ResponseEntity.ok(usuarioEventoService.consultarUsuarioEvento(idEvento, idUsuario));
    }

    @GetMapping("/relatorio/presenca-evento/{idEvento}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RelatorioPresencaEventoDto> gerarRelatorioPresencaPorEvento(
            @PathVariable Integer idEvento) {
        return ResponseEntity.ok(usuarioEventoService.gerarRelatorioPresencaPorEvento(idEvento));
    }

    @GetMapping("/relatorio/usuarios-maior-presenca")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<UsuarioMaiorPresencaDto>> getUsuariosComMaiorPresenca() {
        return ResponseEntity.ok(usuarioEventoService.getUsuariosComMaiorPresenca());
    }

    @GetMapping("/relatorio/ranking-presencas/{limite}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<UsuarioMaiorPresencaDto>> getRankingPresencas(
            @PathVariable Integer limite) {
        return ResponseEntity.ok(usuarioEventoService.getRankingPresencas(limite));
    }

    @GetMapping("/relatorio/estatisticas-presenca")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<EstatisticaPresencaDto> getEstatisticasPresenca() {
        return ResponseEntity.ok(usuarioEventoService.getEstatisticasPresenca());
    }

    @GetMapping("/eventos-com-presencas")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<EventoComPresencasDto>> getTodosEventosComPresencas() {
        return ResponseEntity.ok(usuarioEventoService.getTodosEventosComPresencas());
    }
}
