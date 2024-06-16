package com.develhope.spring.exception.customException;

public class OutOfDateException extends RuntimeException {
    public OutOfDateException(String message) {
        super(message);
    }
}
