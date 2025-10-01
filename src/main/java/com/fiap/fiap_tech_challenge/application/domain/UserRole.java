package com.fiap.fiap_tech_challenge.application.domain;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

}
