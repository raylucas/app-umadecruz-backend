package com.umadecruz.app.service;

import com.umadecruz.app.dto.EventoAtualizacaoDto;
import com.umadecruz.app.dto.EventoCriacaoDto;
import com.umadecruz.app.dto.EventoResponseDto;
import com.umadecruz.app.model.Evento;
import com.umadecruz.app.repository.EventoRepository;
import com.umadecruz.app.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository repository;

    private final ModelMapper modelMapper;

    private final UsuarioService usuarioService;

    public EventoResponseDto salvar(EventoCriacaoDto requestDto){
        var evento = modelMapper.map(requestDto, Evento.class);
        var usuarioEvento = usuarioService.consultarPorEmail(requestDto.getEmailUsuario());
        evento.setUsuario(usuarioEvento);
        evento.setDataCriacao(LocalDate.now());
        repository.save(evento);
        return modelMapper.map(evento, EventoResponseDto.class);
    }

    public EventoResponseDto atualizar(EventoAtualizacaoDto requestDto){
        var evento = repository.findById(requestDto.getId()).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        evento.setTitulo(requestDto.getTitulo());
        evento.setDescricao(requestDto.getDescricao());
        evento.setData(requestDto.getData());
        evento.setInicio(requestDto.getInicio());
        evento.setFim(requestDto.getFim());
        repository.save(evento);
        return modelMapper.map(evento, EventoResponseDto.class);
    }

    public List<EventoResponseDto> consultarTodosEventos(){
        var eventos = repository.findAll();
        return MapperUtil.mapList(eventos, EventoResponseDto.class, modelMapper);
    }
}
