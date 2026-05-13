package com.umadecruz.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventoComPresencasDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("idEvento")
    private Integer idEvento;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("data")
    private LocalDate data;

    @JsonProperty("horaInicio")
    private LocalTime inicio;

    @JsonProperty("horaFim")
    private LocalTime fim;

    @JsonProperty("totalPresentes")
    private Integer totalPresentes;

    @JsonProperty("usuariosPresentes")
    private List<UsuarioPresencaDto> usuariosPresentes;

    @JsonProperty("dataCriacao")
    private LocalDate dataCriacao;
}
