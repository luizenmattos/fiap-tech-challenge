package com.fiap.techchallenge1_G13.application.service.impl;

import com.fiap.techchallenge1_G13.application.port.outbound.UserRepositoryPort;
import com.fiap.techchallenge1_G13.application.service.UserUseCase;
import com.fiap.techchallenge1_G13.domain.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class UserServiceImpl implements UserUseCase {

    private final UserRepositoryPort repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositoryPort repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional
    public User create(User user) {
        repository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("Email já cadastrado");
        });

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLastModifiedAt(Instant.now());

        try {
            return repository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> searchByName(String name, Pageable pageable) {
        return repository.findByNameContaining(name, pageable);
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User existing = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        if (!existing.getEmail().equalsIgnoreCase(user.getEmail())) {
            repository.findByEmail(user.getEmail()).ifPresent(u -> { throw new IllegalArgumentException("Email já cadastrado"); });
            existing.setEmail(user.getEmail());
        }
        existing.setName(user.getName());
        existing.setLogin(user.getLogin());
        existing.setLastModifiedAt(Instant.now());
        existing.setAddress(user.getAddress());
        try {
            return repository.save(existing);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
    }

    @Override
    @Transactional
    public void changePassword(Long id, String currentPassword, String newPassword) {
        User existing = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        if (!passwordEncoder.matches(currentPassword, existing.getPassword())) {
            throw new IllegalArgumentException("Senha atual incorreta");
        }
        existing.setPassword(passwordEncoder.encode(newPassword));
        existing.setLastModifiedAt(Instant.now());
        repository.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }
}
