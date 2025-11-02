package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.UserJpaEntity;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

    Optional<UserJpaEntity> findByIdAndDeletedAtIsNull(Long id);

    Optional<UserJpaEntity> findByLoginAndDeletedAtIsNull(String login);

    List<UserJpaEntity> findAllByDeletedAtIsNull();

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_table SET password = :pass WHERE id = :id", nativeQuery = true)
    void changePassword(Long id,String pass);

}
