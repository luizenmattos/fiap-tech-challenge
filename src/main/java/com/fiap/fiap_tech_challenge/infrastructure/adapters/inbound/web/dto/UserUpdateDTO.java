package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDTO {

    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    private String login;
    private AddressDTO address;

}
