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
public class EstatisticaPresencaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("totalUsuarios")
    private Integer totalUsuarios;

    @JsonProperty("totalEventos")
    private Integer totalEventos;

    @JsonProperty("totalPresencas")
    private Integer totalPresencas;

    @JsonProperty("mediaPresencasPorEvento")
    private Double mediaPresencasPorEvento;

    @JsonProperty("mediaPresencasPorUsuario")
    private Double mediaPresencasPorUsuario;

    @JsonProperty("eventoComMaiorPresenca")
    private String eventoComMaiorPresenca;

    @JsonProperty("eventoComMenorPresenca")
    private String eventoComMenorPresenca;
}
