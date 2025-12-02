package com.umadecruz.app.controller;

import com.umadecruz.app.dto.*;
import com.umadecruz.app.service.AvisoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aviso")
@RequiredArgsConstructor
public class AvisoController {

    private final AvisoService avisoService;

    @PostMapping
    public ResponseEntity<AvisoDto> salvar(@RequestBody AvisoCriacaoDto dto){
        return ResponseEntity.ok(avisoService.salvar(dto));
    }

    @PutMapping
    public ResponseEntity<AvisoDto> atualizar(@RequestBody AvisoAtualizacaoDto dto){
        return ResponseEntity.ok(avisoService.atualizar(dto));
    }

    @GetMapping
    public ResponseEntity<List<AvisoDto>> buscarTodos(){
        return ResponseEntity.ok(avisoService.consultarTodosAvisos());
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> excluir(@PathVariable Integer id){
        try {
            avisoService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
