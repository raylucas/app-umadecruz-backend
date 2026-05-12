package com.umadecruz.app.service;

import com.umadecruz.app.dto.*;
import com.umadecruz.app.model.Aviso;
import com.umadecruz.app.model.Token;
import com.umadecruz.app.repository.AvisoRepository;
import com.umadecruz.app.exception.AvisoNaoEncontradoException;
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
public class AvisoService {

    private final AvisoRepository repository;

    private final ModelMapper modelMapper;

    private final UsuarioService usuarioService;

    private final PushNotificationService pushNotificationService;

    private final TokenService tokenService;

    public AvisoDto salvar(AvisoCriacaoDto dto){
        var aviso = modelMapper.map(dto, Aviso.class);
        var usuarioAviso = usuarioService.consultarPorId(dto.getIdUsuario());
        aviso.setUsuario(usuarioAviso);
        aviso.setDataCriacao(LocalDate.now());
        repository.save(aviso);

        this.enviarNotificacaoAviso(aviso);

        return modelMapper.map(aviso, AvisoDto.class);
    }

    public AvisoDto atualizar(AvisoAtualizacaoDto dto){
        var aviso = repository.findById(dto.getId()).orElseThrow(AvisoNaoEncontradoException::new);
        aviso.setTitulo(dto.getTitulo());
        aviso.setCorpo(dto.getCorpo());
        repository.save(aviso);
        return modelMapper.map(aviso, AvisoDto.class);
    }

    public List<AvisoDto> consultarAvisosHoje(){
        LocalDate hoje = LocalDate.now();
        var avisos = repository.findByDataCriacao(hoje);
        return MapperUtil.mapList(avisos, AvisoDto.class, modelMapper);
    }

    public void excluir(Integer id){
        var aviso = repository.findById(id).orElseThrow(AvisoNaoEncontradoException::new);
        repository.delete(aviso);
    }

    private void enviarNotificacaoAviso(Aviso aviso){
        var tokens = tokenService.consultarTodosTokens()
                .stream()
                .map(Token::getToken)
                .toList();

        pushNotificationService.enviarParaTokens(
                tokens,
                aviso.getTitulo(),
                aviso.getCorpo()
        );
    }


}