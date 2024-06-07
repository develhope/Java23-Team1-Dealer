package com.develhope.spring.user.dto;

import com.develhope.spring.user.entity.UserKind;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private String name;
    @NotBlank(message = "This field can't be empty ")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "can't contains numbers or special character, please insert a valid input")
    private String surname;
    private String mobile;
    @NotBlank(message = "This field can't be empty ")
    @Email
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserKind userKind;
}
