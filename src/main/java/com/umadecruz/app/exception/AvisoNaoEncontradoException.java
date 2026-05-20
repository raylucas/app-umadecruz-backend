package com.umadecruz.app.exception;

public class AvisoNaoEncontradoException extends RuntimeException {
    public AvisoNaoEncontradoException() {
        super("Aviso não encontrado");
    }
}
