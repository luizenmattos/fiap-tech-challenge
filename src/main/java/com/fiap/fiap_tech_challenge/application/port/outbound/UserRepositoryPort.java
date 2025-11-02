package com.fiap.fiap_tech_challenge.application.port.outbound;

import com.fiap.fiap_tech_challenge.application.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> save(User user);
    
    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByLogin(String login);

    void changePassword(Long id,String pass);
}

