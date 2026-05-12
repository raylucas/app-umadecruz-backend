package com.umadecruz.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.umadecruz.app.enumeration.CategoriaMovimentacao;
import com.umadecruz.app.enumeration.StatusMovimentacao;
import com.umadecruz.app.enumeration.TipoConta;
import com.umadecruz.app.enumeration.TipoMovimentacao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class MovimentacaoRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "O campo tipoMovimentacao é obrigatório.")
    @JsonProperty("tipoMovimentacao")
    private TipoMovimentacao tipoMovimentacao;

    @NotNull(message = "O campo tipoConta é obrigatório.")
    @JsonProperty("tipoConta")
    private TipoConta tipoConta;

    @NotNull(message = "O campo categoria é obrigatório.")
    @JsonProperty("categoria")
    private CategoriaMovimentacao categoria;

    @NotBlank(message = "O campo descricao é obrigatório.")
    @JsonProperty("descricao")
    private String descricao;

    @NotNull(message = "O campo valor é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
    @JsonProperty("valor")
    private BigDecimal valor;

    @NotNull(message = "O campo dataMovimentacao é obrigatório.")
    @JsonProperty("dataMovimentacao")
    private LocalDate dataMovimentacao;

    @NotNull(message = "O campo status é obrigatório.")
    @JsonProperty("status")
    private StatusMovimentacao status;

    @JsonProperty("observacao")
    private String observacao;

}
