package com.umadecruz.app.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum CategoriaMovimentacao {

    VENDA,
    COMPRA,
    DOACAO,
    OUTROS;

}
