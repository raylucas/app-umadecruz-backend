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


@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDto> salvar(@RequestBody UsuarioCriacaoDto dto){
        return ResponseEntity.ok(usuarioService.salvar(dto));
    }

    @PutMapping
    public ResponseEntity<UsuarioDto> atualizar(@RequestBody UsuarioAtualizacaoDto dto){
        return ResponseEntity.ok(usuarioService.atualizar(dto));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(usuarioService.buscarInfoUsuario(id));
    }

    @PutMapping("/alterarSenha")
    public ResponseEntity<?> excluir(@RequestBody AlterarSenhaDto dto){
        try {
            usuarioService.alterarSenha(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
