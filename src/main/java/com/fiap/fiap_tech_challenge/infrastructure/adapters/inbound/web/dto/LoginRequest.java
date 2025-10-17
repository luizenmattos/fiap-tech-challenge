package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String login;
    private String password;

}
