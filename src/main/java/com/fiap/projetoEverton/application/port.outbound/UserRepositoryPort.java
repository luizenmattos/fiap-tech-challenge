package com.fiap.techchallenge1_G13.application.port.outbound;

import com.fiap.techchallenge1_G13.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepositoryPort {

    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByLogin(String login);
    Page<User> findByNameContaining(String name, Pageable pageable);
    void deleteById(Long id);
    boolean existsById(Long id);
}
