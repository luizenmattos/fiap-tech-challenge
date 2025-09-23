package com.fiap.techchallenge1_G13.adapters.inbound.web.dto;

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
