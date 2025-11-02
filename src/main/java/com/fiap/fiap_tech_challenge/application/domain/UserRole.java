package com.fiap.fiap_tech_challenge.application.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {
    OWNER,
    CLIENT;

    @JsonCreator
    public static UserRole fromExternal(String value) {
        if (value == null) {
            return null;
        }

        switch (value.toUpperCase()) {
            case "OWNER":
                return OWNER;

            case "CLIENT":
                return CLIENT;
         
            default:
                return null;
        }
    }

}
