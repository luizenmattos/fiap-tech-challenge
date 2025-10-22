package com.fiap.fiap_tech_challenge.application.domain;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return name;
    }

}
