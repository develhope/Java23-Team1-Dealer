package com.develhope.spring.user.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException (String message) {
        super(message);
    }
}
