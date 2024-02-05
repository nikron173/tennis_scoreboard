package com.nikron.tennis.exception;

public class NotFoundResourceException extends ApplicationException {
    public NotFoundResourceException(String message, int statusCode) {
        super(message, statusCode);
    }
}
