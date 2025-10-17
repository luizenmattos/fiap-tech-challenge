package com.fiap.fiap_tech_challenge.application.port.inbound;

public record UserUpdateInput(
    String firstName,
    String lastName,
    String phone,
    String countryCode,
    String postalCode,
    String state,
    String city,
    String street,
    String number,
    String complement
) {

    

}
