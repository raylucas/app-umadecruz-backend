package com.umadecruz.app.service;

import com.umadecruz.app.dto.*;
import com.umadecruz.app.exception.UsuarioEventoNaoEncontradoException;
import com.umadecruz.app.model.UsuarioEvento;
import com.umadecruz.app.model.UsuarioEventoId;
import com.umadecruz.app.repository.UsuarioEventoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public RelatorioPresencaEventoDto gerarRelatorioPresencaPorEvento(Integer idEvento) {
        var evento = eventoService.consultarPorId(idEvento);
        var presencas = repository.findByEventoId(idEvento);
        
        var usuariosPresentes = presencas.stream()
                .map(ue -> UsuarioPresencaDto.builder()
                        .idUsuario(ue.getUsuario().getId())
                        .nomeUsuario(ue.getUsuario().getNome())
                        .email(ue.getUsuario().getEmail())
                        .congregacao(ue.getUsuario().getCongregacao())
                        .dataPresenca(ue.getData())
                        .build())
                .collect(Collectors.toList());

        return RelatorioPresencaEventoDto.builder()
                .idEvento(evento.getId())
                .tituloEvento(evento.getTitulo())
                .dataEvento(evento.getData())
                .horaInicio(evento.getInicio())
                .horaFim(evento.getFim())
                .totalPresentes(usuariosPresentes.size())
                .usuariosPresentes(usuariosPresentes)
                .build();
    }

    public List<UsuarioMaiorPresencaDto> getUsuariosComMaiorPresenca() {
        var resultados = repository.findUsuariosComMaiorPresenca();
        
        return resultados.stream()
                .map((resultado) -> {
                    Integer idUsuario = (Integer) resultado[0];
                    Long totalPresencas = (Long) resultado[1];
                    
                    var usuario = usuarioService.consultarPorId(idUsuario);
                    
                    return UsuarioMaiorPresencaDto.builder()
                            .idUsuario(usuario.getId())
                            .nomeUsuario(usuario.getNome())
                            .email(usuario.getEmail())
                            .congregacao(usuario.getCongregacao())
                            .totalPresencas(totalPresencas.intValue())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public List<UsuarioMaiorPresencaDto> getRankingPresencas(Integer limite) {
        var usuarios = getUsuariosComMaiorPresenca();
        
        var ranking = usuarios.stream()
                .limit(limite)
                .collect(Collectors.toList());
        
        for (int i = 0; i < ranking.size(); i++) {
            ranking.get(i).setPosicaoRanking(i + 1);
        }
        
        return ranking;
    }

    public EstatisticaPresencaDto getEstatisticasPresenca() {
        Integer totalUsuariosComPresenca = repository.countUsuariosComPresenca();
        Integer totalEventosComPresenca = repository.countEventosComPresenca();
        Integer totalPresencas = repository.countTotalPresencas();
        
        Double mediaPresencasPorEvento = totalEventosComPresenca > 0 ? 
                (double) totalPresencas / totalEventosComPresenca : 0.0;
        Double mediaPresencasPorUsuario = totalUsuariosComPresenca > 0 ? 
                (double) totalPresencas / totalUsuariosComPresenca : 0.0;
        
        var eventosComMaiorPresenca = repository.findEventosComMaiorPresenca();
        String eventoComMaiorPresenca = eventosComMaiorPresenca.isEmpty() ? "N/A" : 
                eventoService.consultarPorId((Integer) eventosComMaiorPresenca.get(0)[0]).getTitulo();
        
        String eventoComMenorPresenca = eventosComMaiorPresenca.isEmpty() ? "N/A" : 
                eventoService.consultarPorId((Integer) eventosComMaiorPresenca
                        .get(eventosComMaiorPresenca.size() - 1)[0]).getTitulo();
        
        return EstatisticaPresencaDto.builder()
                .totalUsuarios(totalUsuariosComPresenca)
                .totalEventos(totalEventosComPresenca)
                .totalPresencas(totalPresencas)
                .mediaPresencasPorEvento(mediaPresencasPorEvento)
                .mediaPresencasPorUsuario(mediaPresencasPorUsuario)
                .eventoComMaiorPresenca(eventoComMaiorPresenca)
                .eventoComMenorPresenca(eventoComMenorPresenca)
                .build();
    }

    public List<EventoComPresencasDto> getTodosEventosComPresencas() {
        var eventos = repository.findAllEventosComPresenca();
        
        return eventos.stream()
                .map(evento -> {
                    var presencas = repository.findByEventoId(evento.getId());
                    
                    var usuariosPresentes = presencas.stream()
                            .map(ue -> UsuarioPresencaDto.builder()
                                    .idUsuario(ue.getUsuario().getId())
                                    .nomeUsuario(ue.getUsuario().getNome())
                                    .email(ue.getUsuario().getEmail())
                                    .congregacao(ue.getUsuario().getCongregacao())
                                    .dataPresenca(ue.getData())
                                    .build())
                            .collect(Collectors.toList());

                    return EventoComPresencasDto.builder()
                            .idEvento(evento.getId())
                            .titulo(evento.getTitulo())
                            .descricao(evento.getDescricao())
                            .data(evento.getData())
                            .inicio(evento.getInicio())
                            .fim(evento.getFim())
                            .totalPresentes(usuariosPresentes.size())
                            .usuariosPresentes(usuariosPresentes)
                            .dataCriacao(evento.getDataCriacao())
                            .build();
                })
                .collect(Collectors.toList());
    }

}
