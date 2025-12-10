package com.umadecruz.app.service;

import com.umadecruz.app.dto.UsuarioEventoDto;
import com.umadecruz.app.dto.UsuarioEventoPresencaDto;
import com.umadecruz.app.exception.UsuarioEventoNaoEncontradoException;
import com.umadecruz.app.model.UsuarioEvento;
import com.umadecruz.app.model.UsuarioEventoId;
import com.umadecruz.app.repository.UsuarioEventoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioEventoService {

    private final UsuarioEventoRepository repository;

    private final EventoService eventoService;

    private final UsuarioService usuarioService;

    private final ModelMapper modelMapper;

    public UsuarioEventoDto salvar(UsuarioEventoPresencaDto dto){

        var evento = eventoService.consultarPorId(dto.getIdEvento());
        var usuario = usuarioService.consultarPorId(dto.getIdUsario());

        var usuarioEvento = UsuarioEvento.builder()
                .id(UsuarioEventoId.builder()
                        .idEvento(evento.getId())
                        .idUsuario(usuario.getId())
                        .build())
                .usuario(usuario)
                .evento(evento)
                .data(LocalDateTime.now())
                .build();

        repository.save(usuarioEvento);
        return modelMapper.map(usuarioEvento, UsuarioEventoDto.class);
    }

    public UsuarioEventoDto consultarUsuarioEvento(Integer idEvento, Integer idUsuario){
        var usuarioEvento = repository.findByEventoIdAndUsuarioId(idEvento, idUsuario).orElseThrow(UsuarioEventoNaoEncontradoException::new);
        return modelMapper.map(usuarioEvento, UsuarioEventoDto.class);
    }

}
