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

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O campo token é obrigatório.")
    @JsonProperty("token")
    private String token;

    @NotBlank(message = "O campo idUsuario é obrigatório.")
    @JsonProperty("idUsuario")
    private Integer idUsuario;

    @NotBlank(message = "O campo plataforma é obrigatório.")
    @JsonProperty("plataforma")
    private String plataforma;
}