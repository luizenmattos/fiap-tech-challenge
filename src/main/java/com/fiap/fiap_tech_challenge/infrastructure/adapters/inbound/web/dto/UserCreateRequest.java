package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto;

import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateRequest {

    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
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

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp="^\\d{5}-?\\d{3}$", message="CEP no formato 00000-000 ou 00000000")
    private String postalCode;
    @NotBlank(message = "estado é obrigatório")
    private String state;
    @NotBlank(message = "cidade é obrigatório")
    private String city;
    @NotBlank(message = "rua é obrigatório")
    private String street;
    @NotBlank(message = "número é obrigatório")
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
