package com.nikron.tennis.exception;

public class BadRequestException extends ApplicationException {
    public BadRequestException(String message, int statusCode) {
        super(message, statusCode);
    }
}
