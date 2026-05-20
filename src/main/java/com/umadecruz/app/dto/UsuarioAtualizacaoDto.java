package com.umadecruz.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioAtualizacaoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O campo id é obrigatório.")
    @JsonProperty("id")
    private Integer id;

    @NotBlank(message = "O campo nome é obrigatório.")
    @JsonProperty("nome")
    private String nome;

    @NotBlank(message = "O campo dataNascimento é obrigatório.")
    @JsonProperty("dataNascimento")
    private LocalDate dataNascimento;

    @NotBlank(message = "O telefone nome é obrigatório.")
    @JsonProperty("telefone")
    private String telefone;

    @NotBlank(message = "O endereco nome é obrigatório.")
    @JsonProperty("endereco")
    private String endereco;

    @NotBlank(message = "O bairro nome é obrigatório.")
    @JsonProperty("bairro")
    private String bairro;

    @NotBlank(message = "O cidade nome é obrigatório.")
    @JsonProperty("cidade")
    private String cidade;

    @NotBlank(message = "O cep nome é obrigatório.")
    @JsonProperty("cep")
    private String cep;

    @NotBlank(message = "O congregacao nome é obrigatório.")
    @JsonProperty("congregacao")
    private String congregacao;

    @NotBlank(message = "O campo dataBatismo é obrigatório.")
    @JsonProperty("dataBatismo")
    private LocalDate dataBatismo;

    @JsonProperty("foto")
    private String foto;

}
