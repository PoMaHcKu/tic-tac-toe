package com.herokuapp.crosses.exception;

public class UnrecognizedGamerException extends RuntimeException {

    public UnrecognizedGamerException() {
    }

    public UnrecognizedGamerException(String message) {
        super(message);
    }
}
