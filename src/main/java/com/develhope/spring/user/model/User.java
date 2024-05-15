package com.develhope.spring.user.model;

import com.develhope.spring.user.entity.UserKind;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String surname;

    private long mobile;

    private String email;

    private long password;

    private UserKind userKind;

    private void checkValidString(String value, String fieldName) throws InvalidValueException {
        String rightValue = value.trim();
        if (rightValue.isEmpty() || !rightValue.chars().allMatch(Character::isLetter)) {
            throw new InvalidValueException("This field can't be empty and can't contains numbers or special character, please insert a valid input");
        } else {
            if (fieldName.equals("Name")) {
                this.name = rightValue;
            } else if (fieldName.equals("Surname")) {
                this.surname = rightValue;
            }
        }
    }

    public User(String name, String surname, String email, long mobile, long password, UserKind userKind) throws InvalidValueException, InvalidEmailException {
        checkValidString(name, "Name");
        checkValidString(surname, "Surname");
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.userKind = userKind;
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

    public void setName(String name) throws InvalidValueException {
        checkValidString(name, "Name");
    }

    public void setSurname(String surname) throws InvalidValueException {
        checkValidString(surname, "Surname");
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
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mobile=" + mobile +
                ", email='" + email + '\'' +
                ", userKind=" + userKind +
                '}';
    }
}