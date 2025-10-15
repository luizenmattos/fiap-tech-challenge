package com.fiap.fiap_tech_challenge.application.port.inbound;

import com.fiap.fiap_tech_challenge.application.domain.Person;
import com.fiap.fiap_tech_challenge.application.domain.User;

public record UserUpdateOutput(
    Long id,
    String firstName,
    String lastName,
    String phone
) {

    public static UserUpdateOutput newInstance(User user, Person person){
        return new UserUpdateOutput(user.getId(), person.getFirstName(), person.getLastName(), person.getPhone());
    }

}
