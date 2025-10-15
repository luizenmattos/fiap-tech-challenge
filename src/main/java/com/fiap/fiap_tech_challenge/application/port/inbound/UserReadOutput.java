package com.fiap.fiap_tech_challenge.application.port.inbound;

import com.fiap.fiap_tech_challenge.application.domain.Person;

public record UserReadOutput(Long id, String firstName, String lastName, String phone) {


    public static UserReadOutput newInstance(Long userId, Person person){
        return new UserReadOutput(userId, person.getFirstName(), person.getLastName(), person.getPhone());
    }
}
