package com.develhope.spring.exception;

public class NoResultsException extends RuntimeException{
    public NoResultsException(String message) {
        super(message);
    }
}
