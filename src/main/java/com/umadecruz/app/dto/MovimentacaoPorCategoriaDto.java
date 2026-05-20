package com.umadecruz.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.umadecruz.app.enumeration.CategoriaMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovimentacaoPorCategoriaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("categoria")
    private CategoriaMovimentacao categoria;

    @JsonProperty("totalEntradas")
    private BigDecimal totalEntradas;

    @JsonProperty("totalSaidas")
    private BigDecimal totalSaidas;

    @JsonProperty("saldo")
    private BigDecimal saldo;

}
