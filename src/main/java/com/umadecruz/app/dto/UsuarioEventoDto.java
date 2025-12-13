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
public class UsuarioEventoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("usuario")
    private UsuarioDto usuario;

    @JsonProperty("evento")
    private EventoDto evento;
}
