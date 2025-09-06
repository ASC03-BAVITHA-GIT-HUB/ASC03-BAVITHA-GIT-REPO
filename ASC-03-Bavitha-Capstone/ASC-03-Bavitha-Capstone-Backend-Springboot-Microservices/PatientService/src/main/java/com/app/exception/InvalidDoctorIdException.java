package com.app.exception;

public class InvalidDoctorIdException extends RuntimeException {
    public InvalidDoctorIdException(String message) {
        super(message);
    }
}
