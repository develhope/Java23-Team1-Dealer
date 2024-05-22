package com.develhope.spring.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "`user`")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "This field can't be empty ")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "can't contains numbers or special character, please insert a valid input")
    private String name;
    @NotBlank(message = "This field can't be empty ")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "can't contains numbers or special character, please insert a valid input")
    private String surname;
    private String mobile;
    @NotBlank(message = "This field can't be empty ")
    @Email
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserKind userKind;
}