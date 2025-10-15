package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto;

import org.springframework.security.core.userdetails.User;

import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateInput;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDTO {


    private String name;
    
    private String firstName;
    
    private String lastName;
    
    private String phone;
    
    private String login;
    
    @Size(min = 8)
    private String password;
    
    @Email
    private String email;
    
    private AddressDTO address;
    
    private String role;

    public UserCreateInput toInput(){
        return new UserCreateInput(this.login, this.password, this.firstName, this.lastName, this.phone);
    }

}
