package com.umadecruz.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.umadecruz.app.enumeration.CategoriaMovimentacao;
import com.umadecruz.app.enumeration.StatusMovimentacao;
import com.umadecruz.app.enumeration.TipoConta;
import com.umadecruz.app.enumeration.TipoMovimentacao;
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
public class MaiorMovimentacaoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("valor")
    private BigDecimal valor;

    @JsonProperty("tipoMovimentacao")
    private TipoMovimentacao tipoMovimentacao;

    @JsonProperty("tipoConta")
    private TipoConta tipoConta;

    @JsonProperty("categoria")
    private CategoriaMovimentacao categoria;

    @JsonProperty("dataMovimentacao")
    private LocalDate dataMovimentacao;

    @JsonProperty("status")
    private StatusMovimentacao status;

    @JsonProperty("ranking")
    private Integer ranking;

}
