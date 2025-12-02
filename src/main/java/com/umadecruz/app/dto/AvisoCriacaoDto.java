package com.umadecruz.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvisoCriacaoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("corpo")
    private String corpo;

    @JsonProperty("idUsuario")
    private Integer idUsuario;

}