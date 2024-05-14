package com.develhope.spring.user.model;

import com.develhope.spring.user.entity.UserKind;
import jakarta.persistence.Entity;

@Entity
public class User {

    private String name;

    private String surname;

    private long mobile;

    private String email;

    private long password;

    private UserKind userKind;

    private static boolean isValidString(String value) {
        return !value.isEmpty() && value.chars().allMatch(Character::isLetter);
    }

    private void checkValidName(String name) throws InvalidValueException {
        String rightName = name.trim(); //rimuove i possibili spazi nella stringa
        if (rightName.isEmpty() || !rightName.chars().allMatch(Character::isLetter)) {
            throw new InvalidValueException("This field can't be empty and can't contains numbers or special character, please insert a valid input (Name)");
        } else {
            this.name = rightName;
        }
    }

    private void checkValidSurname(String surname) throws InvalidValueException {
        String rightSurname = surname.trim();
        if (rightSurname.isEmpty() || !rightSurname.chars().allMatch(Character::isLetter)) {
            throw new InvalidValueException("This field can't be empty and can't contains numbers or special character, please insert a valid input (Surname)");
        } else {
            this.name = rightSurname;
        }
    }

    public User(String name, String surname, String email, long mobile, long password, UserKind userKind) throws InvalidValueException, InvalidEmailException {
        checkValidName(name);
        checkValidSurname(surname);
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
        checkValidName(name);
    }

    public void setSurname(String surname) throws InvalidValueException {
        checkValidSurname(surname);
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