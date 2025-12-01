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
import java.time.LocalTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventoCriacaoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O campo titulo é obrigatório.")
    @JsonProperty("titulo")
    private String titulo;

    @NotBlank(message = "O campo descricao é obrigatório.")
    @JsonProperty("descricao")
    private String descricao;

    @NotBlank(message = "O campo data é obrigatório.")
    @JsonProperty("data")
    private LocalDate data;

    @NotBlank(message = "O campo inicio é obrigatório.")
    @JsonProperty("inicio")
    private LocalTime inicio;

    @NotBlank(message = "O campo fim é obrigatório.")
    @JsonProperty("fim")
    private LocalTime fim;

    @NotBlank(message = "O campo emailUsuario é obrigatório.")
    @JsonProperty("emailUsuario")
    private String emailUsuario;

}
