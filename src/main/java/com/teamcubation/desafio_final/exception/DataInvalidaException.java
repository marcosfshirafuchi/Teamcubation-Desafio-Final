package com.teamcubation.desafio_final.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DataInvalidaException extends RuntimeException {

    public DataInvalidaException(String s) {
    }
}
