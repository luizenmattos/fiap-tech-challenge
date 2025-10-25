package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.UserJpaEntity;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository.UserJpaRepository;
import com.fiap.fiap_tech_challenge.application.port.outbound.UserRepositoryPort;
import com.fiap.fiap_tech_challenge.application.domain.User;
import com.fiap.fiap_tech_challenge.application.domain.UserRole;

// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserReposotoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;

    public UserReposotoryAdapter(UserJpaRepository userJpaRepository){
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> save(User user) {
        UserJpaEntity entity = toEntity(user);
        UserJpaEntity saved = userJpaRepository.save(entity);
        return Optional.of(toDomain(saved));
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findByIdAndDeletedAtIsNull(id).map(this::toDomain);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userJpaRepository.findByLoginAndDeletedAtIsNull(login).map(this::toDomain);
    }

    // mappers simples
    private User toDomain(UserJpaEntity e) {
        if (e == null) return null;
        User u = new User();

        u.setId(e.getId());
        u.setLogin(e.getLogin());
        u.setPassword(e.getPassword());
        u.setRole(UserRole.valueOf(e.getRole()));

        return u;
    }

    private UserJpaEntity toEntity(User u) {
        if (u == null) return null;
        UserJpaEntity e = new UserJpaEntity();
        e.setId(u.getId());
        e.setLogin(u.getLogin());
        e.setPassword(u.getPassword());
        e.setRole(u.getRole().name());
        e.setCreatedAt(u.getCreatedAt());
        e.setUpdatedAt(u.getUpdatedAt());
        e.setDeletedAt(u.getDeletedAt());

        return e;
    }
}
