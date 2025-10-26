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

    @jakarta.validation.constraints.Email
    private String email;

    @jakarta.validation.constraints.NotBlank
    @jakarta.validation.constraints.Pattern(
            regexp = "(?i)^(restaurant_owner|restaurante_owner|owner|admin|client|cliente|user|dono\\ de\\ restaurante)$",
            message = "role deve ser 'restaurant_owner' ou 'client'"
    )
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
