package com.fiap.fiap_tech_challenge.application.domain;

import java.time.Instant;

import lombok.Data;

@Data
public class Person {
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;

    private String email;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    
    public static Person newInstance(Long userId, String firstName, String lastName, String phone, String email){
        Person person = new Person();
        person.userId = userId;
        person.firstName = firstName;
        person.lastName = lastName;
        person.phone = phone;
        person.email = email;
        person.createdAt = Instant.now();
        person.updatedAt = Instant.now();
        
        return person;
    }

    public void updatePersonalInfo(String firstName, String lastName, String phone, String email){
        if (firstName != null) this.firstName = firstName;
        if (lastName != null) this.lastName = lastName;
        if (phone != null) this.phone = phone;
        if (email != null) this.email = email;

        this.updatedAt = Instant.now();
    }

    public void delete(){
        this.deletedAt = Instant.now();
    }

}
