package com.app.exception;

public class InvalidHospitalIdException extends RuntimeException {
    public InvalidHospitalIdException(String message) {
        super(message);
    }
}

