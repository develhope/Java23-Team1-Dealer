package com.develhope.spring.exception.customException;

public class UserWithoutPrivilegeException extends RuntimeException{
    public UserWithoutPrivilegeException(String message) {
        super(message);
    }
}
