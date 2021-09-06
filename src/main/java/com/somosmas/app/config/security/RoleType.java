package com.somosmas.app.config.security;

public enum RoleType {

    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");
    private final String description;

    RoleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
