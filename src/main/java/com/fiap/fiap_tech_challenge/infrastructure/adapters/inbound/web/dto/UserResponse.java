package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateOutput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserReadOutput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserUpdateOutput;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data   
public class UserResponse {

    private Long id;
    private String login;
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
    
    public static UserResponse fromOutput(UserCreateOutput output){
        var response = new UserResponse(); 
        response.setId(output.id());
        response.setLogin(output.login());
        response.setFirstName(output.firstName());
        response.setLastName(output.lastName());
        response.setPhone(output.phone());
        response.setCountryCode(output.countryCode());
        response.setPostalCode(output.postalCode());
        response.setState(output.state());
        response.setCity(output.city());
        response.setStreet(output.street());
        response.setNumber(output.number());
        response.setComplement(output.complement());

        return response ;
    }

    public static UserResponse fromOutput(UserReadOutput output){
        var response = new UserResponse(); 
        response.setId(output.id());
        response.setFirstName(output.firstName());
        response.setLastName(output.lastName());
        response.setPhone(output.phone());
        response.setCountryCode(output.countryCode());
        response.setPostalCode(output.postalCode());
        response.setState(output.state());
        response.setCity(output.city());
        response.setStreet(output.street());
        response.setNumber(output.number());
        response.setComplement(output.complement());
        return response;
    }

    public static UserResponse fromOutput(UserUpdateOutput output){
        var response = new UserResponse(); 
        response.setId(output.id());
        response.setFirstName(output.firstName());
        response.setLastName(output.lastName());
        response.setPhone(output.phone());
        response.setCountryCode(output.countryCode());
        response.setPostalCode(output.postalCode());
        response.setState(output.state());
        response.setCity(output.city());
        response.setStreet(output.street());
        response.setNumber(output.number());
        response.setComplement(output.complement());
        return response;
    }

}
