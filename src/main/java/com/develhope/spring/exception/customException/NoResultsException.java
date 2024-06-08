package com.develhope.spring.exception.customException;

public class NoResultsException extends RuntimeException{
    public NoResultsException(String message) {
        super(message);
    }
}
