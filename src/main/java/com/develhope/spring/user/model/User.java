package com.develhope.spring.user.model;

import com.develhope.spring.user.entity.UserKind;

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

    private static boolean containsCommonPassword(long password) {
        long[] COMMON_PASSWORDS = {123456L, 654321L, 9876543210L}; //esempi di pass comuni
        for (long commonPassword : COMMON_PASSWORDS) {
            if (password == commonPassword) {
                return true;
            }
        }
        return false;
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

    private void checkValidEmail(String email) throws InvalidEmailException, InvalidValueException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidValueException("This field can't be empty, please insert a valid input (email)");
        }
        if (email.chars().filter(ch -> ch == '@').count() != 1 || !email.substring(email.indexOf('@')).contains(".")) { // Verifica se l'email contiene almeno una @ e se dopo questa ha almeno un .
            throw new InvalidEmailException("invalid input, please insert right params");
        }
        this.email = email.trim();
    }

    private void checkValidPassword(long password) throws InvalidValueException {
        int MIN_PASSWORD_LENGTH = 8;
        long MIN_PASSWORD_VALUE = 100000000L; //esempio valore minimo per pass
        if (password >= MIN_PASSWORD_VALUE &&
                !containsCommonPassword(password)) {
            throw new InvalidValueException("This password does not meet security requirements: " +
                    "should be greater than " + MIN_PASSWORD_VALUE + ", " +
                    "and must contain " + MIN_PASSWORD_LENGTH + " numbers and you should not choose a common password.");
        }
        this.password = password;
    }


    public User(String name, String surname, String email, long mobile, long password, UserKind userKind) throws InvalidValueException, InvalidEmailException {
        checkValidName(name);
        checkValidSurname(surname);
        checkValidEmail(email);
        checkValidPassword(password);
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

    public void setPassword(long password) throws InvalidValueException {
        checkValidPassword(password);
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