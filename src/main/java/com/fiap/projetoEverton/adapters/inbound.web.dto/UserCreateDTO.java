package com.fiap.projetoEverton.adapters.inbound.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDTO {


    @NotBlank
    private String name;
    @NotBlank
    private String login;
    @NotBlank
    @Size(min = 8)
    private String password;
    @NotBlank
    @Email
    private String email;
    private AddressDTO address;
    private String role;

}
