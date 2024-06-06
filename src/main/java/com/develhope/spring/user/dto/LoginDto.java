package com.develhope.spring.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
