package com.fiap.fiap_tech_challenge.application.domain;

import java.time.Instant;

import lombok.Data;

@Data
public class Address {

    private Long id;
    private Long userId;
    private String countryCode;
    private String postalCode;
    private String state;
    private String city;
    private String street;
    private String number;
    private String complement;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    
    public static Address newInstance(Long userId, String countryCode,String postalCode,String state,String city,String street,String number,String complement){
        Address address = new Address();
        address.userId = userId;
        address.countryCode = countryCode;
        address.postalCode = postalCode;
        address.state = state;
        address.city = city;
        address.street = street;
        address.number = number;
        address.complement = complement;
        address.createdAt = Instant.now();
        address.updatedAt = Instant.now();
        
        return address;
    }
    
    public void updateAdress(String countryCode, String postalCode, String state, String city, String street, String number, String complement){
        if (countryCode != null) this.countryCode = countryCode;
        if (postalCode != null) this.postalCode = postalCode;
        if (state != null) this.state = state;
        if (city != null) this.city = city;
        if (street != null) this.street = street;
        if (number != null) this.number = number;
        if (complement != null) this.complement = complement;
        
        this.updatedAt = Instant.now();
    }

    public void delete(){
        this.deletedAt=Instant.now();
    }

}
