package com.umadecruz.app.controller;

import com.umadecruz.app.dto.*;
import com.umadecruz.app.service.EventoService;
import com.umadecruz.app.service.UsuarioEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/evento")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    private final UsuarioEventoService usuarioEventoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<EventoDto> salvar(@RequestBody EventoCriacaoDto dto){
        return ResponseEntity.ok(eventoService.salvar(dto));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<EventoDto> atualizar(@RequestBody EventoAtualizacaoDto dto){
        return ResponseEntity.ok(eventoService.atualizar(dto));
    }

    @GetMapping("/eventos")
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<List<EventoDto>> buscarTodos(){
        return ResponseEntity.ok(eventoService.consultarTodosEventos());
    }

    @GetMapping("/eventos/semana")
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<List<EventoDto>> buscarTodosSemana(){
        return ResponseEntity.ok(eventoService.consultarTodosEventosSemana());
    }

    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        eventoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/usuario/presenca")
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<UsuarioEventoDto> marcarPresencao(@RequestBody UsuarioEventoPresencaDto dto){
        return ResponseEntity.ok(usuarioEventoService.salvar(dto));
    }

    @GetMapping("/{idEvento}/usuario/{idUsuario}")
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<UsuarioEventoDto> consultarUsuarioEvento(@PathVariable Integer idEvento, @PathVariable Integer idUsuario){
        return ResponseEntity.ok(usuarioEventoService.consultarUsuarioEvento(idEvento, idUsuario));
    }

}