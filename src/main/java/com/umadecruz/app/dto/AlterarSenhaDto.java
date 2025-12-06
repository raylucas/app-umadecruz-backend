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
public class AlterarSenhaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O campo email é obrigatório.")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "O campo senhaAntiga é obrigatório.")
    @JsonProperty("senhaAntiga")
    private String senhaAntiga;

    @NotBlank(message = "O campo novaSenha é obrigatório.")
    @JsonProperty("novaSenha")
    private String novaSenha;


}
