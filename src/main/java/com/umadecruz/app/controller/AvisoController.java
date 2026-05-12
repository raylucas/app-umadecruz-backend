package com.umadecruz.app.controller;

import com.umadecruz.app.dto.*;
import com.umadecruz.app.service.AvisoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/aviso")
@RequiredArgsConstructor
public class AvisoController {

    private final AvisoService avisoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<AvisoDto> salvar(@RequestBody AvisoCriacaoDto dto){
        return ResponseEntity.ok(avisoService.salvar(dto));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<AvisoDto> atualizar(@RequestBody AvisoAtualizacaoDto dto){
        return ResponseEntity.ok(avisoService.atualizar(dto));
    }

    @GetMapping("/avisos/hoje")
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<List<AvisoDto>> buscarTodos(){
        return ResponseEntity.ok(avisoService.consultarAvisosHoje());
    }

    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<?> excluir(@PathVariable Integer id){
        avisoService.excluir(id);
        return ResponseEntity.ok().build();
    }

}
