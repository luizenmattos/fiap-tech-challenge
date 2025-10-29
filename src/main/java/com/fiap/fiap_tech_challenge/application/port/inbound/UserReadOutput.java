package com.fiap.fiap_tech_challenge.application.port.inbound;

import com.fiap.fiap_tech_challenge.application.domain.Address;
import com.fiap.fiap_tech_challenge.application.domain.Person;

public record UserReadOutput(
    Long id, 
    String firstName, 
    String lastName, 
    String phone,
    String email,
    String countryCode,
    String postalCode,
    String state,
    String city,
    String street,
    String number,
    String complement
    ) {


    public static UserReadOutput newInstance(Long userId, Person person, Address address){
        return new UserReadOutput(
            userId, 
            person.getFirstName(), 
            person.getLastName(), 
            person.getPhone(),
            person.getEmail(),
            address.getCountryCode(),
            address.getPostalCode(),
            address.getState(),
            address.getCity(),
            address.getStreet(),
            address.getNumber(),
            address.getComplement()
        );
    }
}
