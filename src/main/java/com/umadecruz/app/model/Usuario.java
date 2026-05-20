package com.umadecruz.app.model;

import com.umadecruz.app.enumeration.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="USUARIO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "CONGREGACAO")
    private String congregacao;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DATA_BATISMO")
    private LocalDate dataBatismo;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO")
    private TipoUsuario tipo;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "DATA_CRIACAO")
    private LocalDate dataCriacao;

    @Column(name = "FOTO")
    private byte[] foto;
}
