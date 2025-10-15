package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto;

import lombok.Data;

import java.time.Instant;

import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateOutput;

@Data
public class UserResponseDTO {

    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Instant lastModifiedAt;
    private AddressDTO address;
    private String role;

    public static UserResponseDTO fromOutput(UserCreateOutput output){
        var response = new UserResponseDTO(); 
        response.setId(output.id());
        response.setLogin(output.username());
        response.setFirstName(output.firstName());
        response.setLastName(output.lastName());
        response.setPhone(output.phone());
        return response ;
    }

}
