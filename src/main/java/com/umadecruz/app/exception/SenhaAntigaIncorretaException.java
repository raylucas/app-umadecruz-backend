package com.umadecruz.app.exception;

public class SenhaAntigaIncorretaException extends RuntimeException{
    public SenhaAntigaIncorretaException() {
        super("Senha Antiga Incorreta");
    }
}
