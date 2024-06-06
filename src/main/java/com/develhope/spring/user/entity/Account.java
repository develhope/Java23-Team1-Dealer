package com.develhope.spring.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String username;

    @NotBlank(message = "This field can't be empty ")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "can't contains numbers or special character, please insert a valid input")
    private String name;

    @NotBlank(message = "This field can't be empty ")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "can't contains numbers or special character, please insert a valid input")
    private String surname;

    private String mobile;

    @NotBlank(message = "This field can't be empty ")
    @Email
    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserKind userKind;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(userKind);
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}