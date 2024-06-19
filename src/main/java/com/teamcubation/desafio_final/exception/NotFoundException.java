package com.teamcubation.desafio_final.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String mensagem) {
        super(mensagem);
    }
}
