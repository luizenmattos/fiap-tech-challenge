package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto;

import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateInput;
import lombok.Data;

@Data
public class UserCreateRequest {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String role;
    private String countryCode;
    private String postalCode;
    private String state;
    private String city;
    private String street;
    private String number;
    private String complement;


    public UserCreateInput toInput(){
        return new UserCreateInput(
            this.login,
            this.password,
            this.firstName,
            this.lastName,
            this.phone,
            this.email,
            this.role,
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
