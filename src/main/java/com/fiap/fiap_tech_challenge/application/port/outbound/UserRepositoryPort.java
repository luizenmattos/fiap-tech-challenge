package com.fiap.fiap_tech_challenge.application.port.outbound;

import com.fiap.fiap_tech_challenge.application.domain.User;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepositoryPort {

    User save(User user);

    void deleteById(Long id);

    Optional<User> findById(Long id);
    
    UserDetails findByLogin(String login); //ESTA CERTO RETORNAR USER DETAILS???
}

