package com.umadecruz.app.controller;

import com.umadecruz.app.dto.*;
import com.umadecruz.app.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @PostMapping
    public ResponseEntity<EventoResponseDto> salvar(@RequestBody EventoCriacaoDto dto){
        return ResponseEntity.ok(eventoService.salvar(dto));
    }

    @PutMapping
    public ResponseEntity<EventoResponseDto> atualizar(@RequestBody EventoAtualizacaoDto dto){
        return ResponseEntity.ok(eventoService.atualizar(dto));
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDto>> buscarTodos(){
        return ResponseEntity.ok(eventoService.consultarTodosEventos());
    }

}
