package com.umadecruz.app.exception;

public class EventoNaoEncontradoException extends RuntimeException {
    public EventoNaoEncontradoException() {
        super("Evento não encontrado");
    }
}
