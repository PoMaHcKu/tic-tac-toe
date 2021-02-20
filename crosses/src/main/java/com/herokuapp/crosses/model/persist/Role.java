package com.herokuapp.crosses.model.persist;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    PLAYER ("PLAYER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
