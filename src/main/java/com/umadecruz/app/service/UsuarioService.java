package com.umadecruz.app.service;

import com.umadecruz.app.dto.AlterarSenhaDto;
import com.umadecruz.app.dto.UsuarioAtualizacaoDto;
import com.umadecruz.app.dto.UsuarioCriacaoDto;
import com.umadecruz.app.dto.UsuarioDto;
import com.umadecruz.app.exception.SenhaAntigaIncorretaException;
import com.umadecruz.app.exception.UsuarioNaoEncontradoException;
import com.umadecruz.app.model.Usuario;
import com.umadecruz.app.repository.UsuarioRepository;
import com.umadecruz.app.util.SenhaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioService {

    @Value("${app.senha.tamanho}")
    private Integer tamanhoSenha;

    private final UsuarioRepository repository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    public UsuarioDto salvar(UsuarioCriacaoDto dto){
        var usuario = modelMapper.map(dto, Usuario.class);
        var senha = SenhaUtil.gerarSenha(tamanhoSenha);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setDataCriacao(LocalDate.now());
        repository.save(usuario);
        var usuarioDto = modelMapper.map(usuario, UsuarioDto.class);
        usuarioDto.setSenha(senha);
        return usuarioDto;
    }

    public UsuarioDto atualizar(UsuarioAtualizacaoDto dto){
        var usuario = repository.findById(dto.getId()).orElseThrow(UsuarioNaoEncontradoException::new);
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

    public void alterarSenha(AlterarSenhaDto dto){

        var usuario = this.consultarPorEmail(dto.getEmail()).orElseThrow(UsuarioNaoEncontradoException::new);
        if(!passwordEncoder.matches(dto.getSenhaAntiga(), usuario.getSenha())){
            throw new SenhaAntigaIncorretaException();
        }
        usuario.setSenha(passwordEncoder.encode(dto.getNovaSenha()));
        repository.save(usuario);
    }

    public Usuario consultarPorId(Integer id){
        return repository.findById(id).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    public UsuarioDto buscarInfoUsuario(Integer id){
        return modelMapper.map(this.consultarPorId(id), UsuarioDto.class);
    }

    public Optional<Usuario> consultarPorEmail(String email){
        return repository.findByEmail(email);
    }

    public Integer consultarIdPorEmail(String email){
        return repository.findIdByEmail(email);
    }

    public List<UsuarioDto> listarTodos(){
        return repository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDto.class))
                .collect(Collectors.toList());
    }


}
