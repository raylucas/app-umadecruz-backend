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
public class RelatorioPresencaEventoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("idEvento")
    private Integer idEvento;

    @JsonProperty("tituloEvento")
    private String tituloEvento;

    @JsonProperty("dataEvento")
    private LocalDate dataEvento;

    @JsonProperty("horaInicio")
    private LocalTime horaInicio;

    @JsonProperty("horaFim")
    private LocalTime horaFim;

    @JsonProperty("totalPresentes")
    private Integer totalPresentes;

    @JsonProperty("usuariosPresentes")
    private List<UsuarioPresencaDto> usuariosPresentes;
}
