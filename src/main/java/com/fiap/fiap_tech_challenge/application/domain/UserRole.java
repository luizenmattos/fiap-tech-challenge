package com.fiap.fiap_tech_challenge.application.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {
    ADMIN("restaurant_owner"),
    USER("client");

    private final String externalName;

    UserRole(String externalName) {
        this.externalName = externalName;
    }

    /** Nome para exibir em respostas ou logs */
    public String getExternalName() {
        return externalName;
    }

    /** Aceita string externas pela api */
    @JsonCreator
    public static UserRole fromExternal(String value) {
        if (value == null) return USER;
        String v = value.trim().toLowerCase();
        switch (v) {
            case "restaurant_owner":
            case "dono de restaurante":
            case "owner":
            case "admin":
                return ADMIN;
            case "client":
            case "cliente":
            case "user":
            default:
                return USER;
        }
    }

}
