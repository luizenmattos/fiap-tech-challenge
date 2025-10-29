package com.fiap.fiap_tech_challenge.application.port.inbound;

import java.util.List;

import jakarta.transaction.Transactional;

public interface UserCrudPort {
    
    @Transactional
    UserCreateOutput create (String token,UserCreateInput userInput);

    UserReadOutput findById(String token, Long id);

    List<UserReadOutput> findAll(String token);

    List<UserReadOutput> findByName(String token,String name);

    @Transactional
    UserUpdateOutput udpate (String token,Long id, UserUpdateInput userInput);

    @Transactional
    void deleteById (String token,Long id);

    String login(String login, String password);

    void changePassword(String token, UserUpdatePasswordInput password);

}
