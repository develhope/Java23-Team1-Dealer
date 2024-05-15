package com.develhope.spring.user.entity;

import com.develhope.spring.user.model.InvalidEmailException;
import com.develhope.spring.user.model.InvalidValueException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
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

    private long mobile;

    private String email;

    private long password;

    private UserKind userKind;

    public User(String name, String surname, String email, long mobile, long password, UserKind userKind) throws InvalidValueException, InvalidEmailException {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.userKind = userKind;
    }
    
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public long getMobile() {
        return mobile;
    }

    public UserKind getUserKind() {
        return userKind;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUserKind(UserKind userKind) {
        this.userKind = userKind;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public void setPassword(long password) {
        this.password = password;
    }

    public void setAccount(UserKind userKind) {
        this.userKind = userKind;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mobile=" + mobile +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", userKind=" + userKind +
                '}';
    }
}