package com.nikron.tennis.exception;

public class DuplicateResourceException extends ApplicationException{
    public DuplicateResourceException(String message, int statusCode) {
        super(message, statusCode);
    }
}
