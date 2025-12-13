package com.umadecruz.app.service;

import com.umadecruz.app.dto.TokenDto;
import com.umadecruz.app.model.Token;
import com.umadecruz.app.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository repository;

    private final UsuarioService usuarioService;

    public void registrar(TokenDto dto) {
        var usuario = usuarioService.consultarPorId(dto.getIdUsuario());

        repository.deleteByToken(dto.getToken());

        var token = Token.builder()
                .usuario(usuario)
                .token(dto.getToken())
                .plataforma(dto.getPlataforma())
                .dataCriacao(LocalDate.now())
                .build();

        repository.save(token);
    }

    public List<Token> consultarTodosTokens(){
        return repository.findAll();
    }

}