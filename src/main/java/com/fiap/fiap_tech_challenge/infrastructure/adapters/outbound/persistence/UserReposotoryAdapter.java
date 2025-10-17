package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.UserJpaEntity;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository.UserJpaRepository;
import com.fiap.fiap_tech_challenge.application.port.outbound.UserRepositoryPort;
import com.fiap.fiap_tech_challenge.application.domain.User;
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
    public User save(User user) {
        UserJpaEntity entity = toEntity(user);
        UserJpaEntity saved = userJpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(this::toDomain);
    }

    // @Override
    // public UserDetails findByLogin(String login) {
    //     return userJpaRepository.findByLogin(login).get(); 
    // }

    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }

    // mappers simples
    private User toDomain(UserJpaEntity e) {
        if (e == null) return null;
        User u = new User();

        u.setId(e.getId());
        u.setLogin(e.getLogin());
        u.setPassword(e.getPassword());

        return u;
    }

    private UserJpaEntity toEntity(User u) {
        if (u == null) return null;
        UserJpaEntity e = new UserJpaEntity();
        e.setId(u.getId());
        e.setLogin(u.getLogin());
        e.setPassword(u.getPassword());

        return e;
    }
}
