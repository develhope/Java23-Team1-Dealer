package com.develhope.spring.user.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserKind implements GrantedAuthority {
    BUYER, SELLER, ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
