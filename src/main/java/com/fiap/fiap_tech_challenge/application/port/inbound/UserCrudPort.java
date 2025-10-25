package com.fiap.fiap_tech_challenge.application.port.inbound;

import java.util.List;

import jakarta.transaction.Transactional;

public interface UserCrudPort {
    
    @Transactional
    UserCreateOutput create (UserCreateInput userInput);

    UserReadOutput findById(Long id);

    List<UserReadOutput> findAll(String token);

    List<UserReadOutput> findByName(String name);

    @Transactional
    UserUpdateOutput udpate (Long id, UserUpdateInput userInput);

    @Transactional
    void deleteById (Long id);

    String login(String login, String password);

}
