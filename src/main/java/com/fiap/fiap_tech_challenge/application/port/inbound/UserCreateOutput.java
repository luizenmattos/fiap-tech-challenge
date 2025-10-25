package com.fiap.fiap_tech_challenge.application.port.inbound;

import com.fiap.fiap_tech_challenge.application.domain.Address;
import com.fiap.fiap_tech_challenge.application.domain.Person;
import com.fiap.fiap_tech_challenge.application.domain.User;

public record UserCreateOutput(
    Long id,
    String login,
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

    public static UserCreateOutput fromDomain(User user, Person person, Address address){
        return new UserCreateOutput(
            user.getId(), 
            user.getLogin(), 
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
