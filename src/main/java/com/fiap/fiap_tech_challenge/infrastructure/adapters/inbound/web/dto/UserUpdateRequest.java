package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto;

import com.fiap.fiap_tech_challenge.application.port.inbound.UserUpdateInput;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String countryCode;
    private String postalCode;
    private String state;
    private String city;
    private String street;
    private String number;
    private String complement;

    public UserUpdateInput toInput(){
        return new UserUpdateInput(
            this.firstName,
            this.lastName,
            this.phone,
            this.email,
            this.countryCode,
            this.postalCode,
            this.state,
            this.city,
            this.street,
            this.number,
            this.complement
        );
    }
}
