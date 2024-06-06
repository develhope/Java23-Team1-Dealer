package com.develhope.spring.exceptions.customExceptions;

public class NoResultsException extends RuntimeException{
    public NoResultsException(String message) {
        super(message);
    }
}
