package com.fiap.fiap_tech_challenge.application.port.inbound;

public record UserUpdateInput(
    String firstName,
    String lastName,
    String phone
) {
}
