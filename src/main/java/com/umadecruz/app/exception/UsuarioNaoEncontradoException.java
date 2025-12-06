package com.umadecruz.app.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException() {
        super("Usuario não encontrado");
    }
}
