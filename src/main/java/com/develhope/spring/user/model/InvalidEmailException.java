package com.develhope.spring.user.model;

public class InvalidEmailException extends Exception {
    public InvalidEmailException(String message){
        super(message);
    }
}
