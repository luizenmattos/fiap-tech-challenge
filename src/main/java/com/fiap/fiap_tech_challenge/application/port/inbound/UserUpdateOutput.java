package com.fiap.fiap_tech_challenge.application.port.inbound;

import java.time.Instant;

import com.fiap.fiap_tech_challenge.application.domain.Address;
import com.fiap.fiap_tech_challenge.application.domain.Person;
import com.fiap.fiap_tech_challenge.application.domain.User;

public record UserUpdateOutput(
    Long id,
    String role,
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
    String complement,
    Instant updatedAt
) {

    public static UserUpdateOutput newInstance(User user, Person person, Address address){
        return new UserUpdateOutput(
            user.getId(), 
            user.getRole().name(),
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
            address.getComplement(),
            person.getUpdatedAt()
        );
    }

}
