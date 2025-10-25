package com.fiap.fiap_tech_challenge.application.domain;

import java.time.Instant;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String login;
    private String password;
    private UserRole role;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    public static User newInstance(String login, String password){
        User user = new User();
        user.login = login;
        user.password = password;
        user.role = UserRole.USER;
        user.createdAt = Instant.now();
        user.updatedAt = Instant.now();

        return user;
    }

    public void delete(){
        this.deletedAt = Instant.now();
    }

}
