package com.umadecruz.app.controller;

import com.umadecruz.app.dto.*;
import com.umadecruz.app.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @PostMapping
    public ResponseEntity<EventoDto> salvar(@RequestBody EventoCriacaoDto dto){
        return ResponseEntity.ok(eventoService.salvar(dto));
    }

    @PutMapping
    public ResponseEntity<EventoDto> atualizar(@RequestBody EventoAtualizacaoDto dto){
        return ResponseEntity.ok(eventoService.atualizar(dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<EventoDto>> buscarTodos(){
        return ResponseEntity.ok(eventoService.consultarTodosEventos());
    }

    @GetMapping("/eventos/semana")
    public ResponseEntity<List<EventoDto>> buscarTodosSemana(){
        return ResponseEntity.ok(eventoService.consultarTodosEventosSemana());
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> excluir(@PathVariable Integer id){
        try {
            eventoService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
