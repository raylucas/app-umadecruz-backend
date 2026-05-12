package com.umadecruz.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FluxoCaixaDiarioDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("data")
    private LocalDate data;

    @JsonProperty("entradasDoDia")
    private BigDecimal entradasDoDia;

    @JsonProperty("saidasDoDia")
    private BigDecimal saidasDoDia;

    @JsonProperty("saldoDoDia")
    private BigDecimal saldoDoDia;

    @JsonProperty("saldoAcumulado")
    private BigDecimal saldoAcumulado;

}
