package com.fiap.fiap_tech_challenge.application.port.inbound;

import com.fiap.fiap_tech_challenge.application.domain.Person;
import com.fiap.fiap_tech_challenge.application.domain.User;

public record UserCreateOutput(
    Long id,
    String username,
    String firstName,
    String lastName,
    String phone
    ) {

    public static UserCreateOutput newInstance(User user, Person person){
        return new UserCreateOutput(user.getId(), user.getLogin(), person.getFirstName(), person.getLastName(), person.getPhone());
    }
}
