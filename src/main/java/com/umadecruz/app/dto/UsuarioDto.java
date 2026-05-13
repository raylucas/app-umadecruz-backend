package com.umadecruz.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("dataNascimento")
    private LocalDate dataNascimento;

    @JsonProperty("telefone")
    private String telefone;

    @JsonProperty("endereco")
    private String endereco;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("cidade")
    private String cidade;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("congregacao")
    private String congregacao;

    @JsonProperty("email")
    private String email;

    @JsonProperty("dataBatismo")
    private LocalDate dataBatismo;

    @Pattern(regexp = "^(ADMIN|USER|FINANCIAL)$", message = "Campo tipo deve ser ADMIN|USER|FINANCIAL")
    @JsonProperty("tipo")
    private String tipo;

    @JsonProperty("senha")
    private String senha;

    @JsonProperty("dataCriacao")
    private LocalDate dataCriacao;

}
