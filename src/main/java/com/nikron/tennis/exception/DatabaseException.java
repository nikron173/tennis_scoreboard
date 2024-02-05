package com.nikron.tennis.exception;

public class DatabaseException extends ApplicationException{
    public DatabaseException(String message, int statusCode) {
        super(message, statusCode);
    }
}
