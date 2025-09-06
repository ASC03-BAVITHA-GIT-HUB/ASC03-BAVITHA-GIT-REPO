package com.app.exception;

public class InvalidPatientIdException extends RuntimeException {
    public InvalidPatientIdException(String message) {
        super(message);
    }
}

