package com.umadecruz.app.service;

import com.umadecruz.app.dto.UsuarioAtualizacaoDto;
import com.umadecruz.app.dto.UsuarioCriacaoDto;
import com.umadecruz.app.dto.UsuarioDto;
import com.umadecruz.app.model.Usuario;
import com.umadecruz.app.repository.UsuarioRepository;
import com.umadecruz.app.util.SenhaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioService {

    @Value("${app.senha.tamanho}")
    private Integer tamanhoSenha;

    private final UsuarioRepository repository;

    private final ModelMapper modelMapper;

    public UsuarioDto salvar(UsuarioCriacaoDto dto){
        var usuario = modelMapper.map(dto, Usuario.class);
        usuario.setSenha(SenhaUtil.gerarSenha(tamanhoSenha));
        usuario.setDataCriacao(LocalDate.now());
        repository.save(usuario);
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    public UsuarioDto atualizar(UsuarioAtualizacaoDto dto){
        var usuario = repository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        usuario.setNome(dto.getNome());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEndereco(dto.getEndereco());
        usuario.setBairro(dto.getBairro());
        usuario.setCidade(dto.getCidade());
        usuario.setCep(dto.getCep());
        usuario.setCongregacao(dto.getCongregacao());
        usuario.setDataBatismo(dto.getDataBatismo());
        repository.save(usuario);
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    public Usuario consultarPorEmail(String email){
        return repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public UsuarioDto buscarInfoUsuario(String email){
        return modelMapper.map(this.consultarPorEmail(email), UsuarioDto.class);
    }


}
