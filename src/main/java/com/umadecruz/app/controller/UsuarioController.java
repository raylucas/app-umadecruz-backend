package com.umadecruz.app.controller;

import com.umadecruz.app.dto.AlterarSenhaDto;
import com.umadecruz.app.dto.UsuarioCriacaoDto;
import com.umadecruz.app.dto.UsuarioAtualizacaoDto;
import com.umadecruz.app.dto.UsuarioDto;
import com.umadecruz.app.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<UsuarioDto> salvar(@RequestBody UsuarioCriacaoDto dto){
        return ResponseEntity.ok(usuarioService.salvar(dto));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<UsuarioDto> atualizar(@RequestBody UsuarioAtualizacaoDto dto){
        return ResponseEntity.ok(usuarioService.atualizar(dto));
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(usuarioService.buscarInfoUsuario(id));
    }

    @PutMapping("/alterarSenha")
    @PreAuthorize("hasAnyRole('USER', 'FINANCIAL', 'ADMIN')")
    public ResponseEntity<?> excluir(@RequestBody AlterarSenhaDto dto){
        usuarioService.alterarSenha(dto);
        return ResponseEntity.ok().build();
    }

}
