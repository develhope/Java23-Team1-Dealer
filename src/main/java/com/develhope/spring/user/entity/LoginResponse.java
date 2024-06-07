package com.develhope.spring.user.entity;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    private long expiresIn;


}