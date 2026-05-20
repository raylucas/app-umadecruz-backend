package com.umadecruz.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioMaiorPresencaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("idUsuario")
    private Integer idUsuario;

    @JsonProperty("nomeUsuario")
    private String nomeUsuario;

    @JsonProperty("email")
    private String email;

    @JsonProperty("congregacao")
    private String congregacao;

    @JsonProperty("totalPresencas")
    private Integer totalPresencas;

    @JsonProperty("posicaoRanking")
    private Integer posicaoRanking;
}
