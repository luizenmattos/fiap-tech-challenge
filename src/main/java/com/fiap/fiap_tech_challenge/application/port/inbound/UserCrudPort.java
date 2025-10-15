package com.fiap.fiap_tech_challenge.application.port.inbound;

import java.util.List;

public interface UserCrudPort {

    UserCreateOutput create (UserCreateInput userInput);

    UserReadOutput findById(Long id);

    List<UserReadOutput> findAll();

    UserUpdateOutput udpate (Long id, UserUpdateInput userInput);

    void deleteById (Long id);

}
