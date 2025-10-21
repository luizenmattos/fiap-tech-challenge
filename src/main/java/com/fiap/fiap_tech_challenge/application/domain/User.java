package com.fiap.fiap_tech_challenge.application.domain;

import java.time.Instant;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

}
