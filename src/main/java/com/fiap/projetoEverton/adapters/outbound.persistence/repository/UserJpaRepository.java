package com.fiap.techchallenge1_G13.adapters.outbound.persistence.repository;

import com.fiap.techchallenge1_G13.adapters.outbound.persistence.entity.UserJpaEntity;
import com.fiap.techchallenge1_G13.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

    Optional<UserJpaEntity> findByEmailIgnoreCase(String email);
    Optional<UserJpaEntity> findByLogin(String login);

    Page<UserJpaEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
