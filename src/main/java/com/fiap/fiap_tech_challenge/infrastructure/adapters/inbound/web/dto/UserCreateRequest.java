package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto;

import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateInput;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateRequest {


    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String countryCode;
    private String postalCode;
    private String state;
    private String city;
    private String street;
    private String number;
    private String complement;

    @jakarta.validation.constraints.Email
     private String email;
    
    public UserCreateInput toInput(){
        return new UserCreateInput(
            this.login,
            this.password,
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
