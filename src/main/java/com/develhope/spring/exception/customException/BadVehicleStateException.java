package com.develhope.spring.exception.customException;

public class BadVehicleStateException extends RuntimeException{
    public BadVehicleStateException(String message) {
        super(message);
    }
}
