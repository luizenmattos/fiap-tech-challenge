package com.fiap.fiap_tech_challenge.adapters.inbound.web.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UserResponseDTO {

    private Long id;
    private String name;
    private String login;
    private String email;
    private Instant lastModifiedAt;
    private AddressDTO address;
    private String role;

}
