package com.fiap.fiap_tech_challenge.application.domain;

import java.time.Instant;

public class Person {
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    
    public static Person newInstance(Long userId, String firstName, String lastName, String phone){
        Person person = new Person();
        person.userId = userId;
        person.firstName = firstName;
        person.lastName = lastName;
        person.phone = phone;
        person.createdAt = Instant.now();
        person.updatedAt = Instant.now();
        
        return person;
    }

    public void updatePersonalInfo(String firstName, String lastName, String phone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.updatedAt = Instant.now();
    }

    public void delete(){
        this.deletedAt = Instant.now();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

}
