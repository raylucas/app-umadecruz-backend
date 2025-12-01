package com.umadecruz.app.util;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;

@UtilityClass
public class SenhaUtil {

    public String gerarSenha(Integer tamanhoSenha) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder(tamanhoSenha);

        for (int i = 0; i < tamanhoSenha; i++) {
            int index = random.nextInt(caracteres.length());
            senha.append(caracteres.charAt(index));
        }

        return senha.toString();
    }

}
