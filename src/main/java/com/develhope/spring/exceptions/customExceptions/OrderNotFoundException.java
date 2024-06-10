package com.develhope.spring.exceptions.customExceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException (String message) {
        super(message);
    }
}
