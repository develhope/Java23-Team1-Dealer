package com.develhope.spring.exception.customException;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException (String message) {
        super(message);
    }
}
