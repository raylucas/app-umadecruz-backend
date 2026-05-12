package com.umadecruz.app.service;

import com.umadecruz.app.dto.EventoAtualizacaoDto;
import com.umadecruz.app.dto.EventoCriacaoDto;
import com.umadecruz.app.dto.EventoDto;
import com.umadecruz.app.model.Evento;
import com.umadecruz.app.repository.EventoRepository;
import com.umadecruz.app.exception.EventoNaoEncontradoException;
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

    public EventoDto salvar(EventoCriacaoDto requestDto){
        var evento = modelMapper.map(requestDto, Evento.class);
        var usuarioEvento = usuarioService.consultarPorId(requestDto.getIdUsuario());
        evento.setUsuario(usuarioEvento);
        evento.setDataCriacao(LocalDate.now());
        repository.save(evento);
        return modelMapper.map(evento, EventoDto.class);
    }

    public EventoDto atualizar(EventoAtualizacaoDto requestDto){
        var evento = this.consultarPorId(requestDto.getId());
        evento.setTitulo(requestDto.getTitulo());
        evento.setDescricao(requestDto.getDescricao());
        evento.setData(requestDto.getData());
        evento.setInicio(requestDto.getInicio());
        evento.setFim(requestDto.getFim());
        repository.save(evento);
        return modelMapper.map(evento, EventoDto.class);
    }

    public List<EventoDto> consultarTodosEventos(){
        var eventos = repository.findAll();
        return MapperUtil.mapList(eventos, EventoDto.class, modelMapper);
    }

    public List<EventoDto> consultarTodosEventosSemana(){
        LocalDate hoje = LocalDate.now();
        LocalDate fimSemana = hoje.plusDays(6);
        var eventos = repository.findByDataBetween(hoje, fimSemana);
        return MapperUtil.mapList(eventos, EventoDto.class, modelMapper);
    }

    public void excluir(Integer id){
        var evento = repository.findById(id).orElseThrow(EventoNaoEncontradoException::new);
        repository.delete(evento);
    }

    public Evento consultarPorId(Integer id){
        return repository.findById(id).orElseThrow(EventoNaoEncontradoException::new);
    }
}
