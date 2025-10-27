package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.PersonJpaEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonJpaEntity, Long> {

    Optional<PersonJpaEntity> findByUserIdAndDeletedAtIsNull(Long userId);

    Optional<PersonJpaEntity> findByEmailAndDeletedAtIsNull(String email);

    List<PersonJpaEntity> findAllByDeletedAtIsNull();
    Optional<PersonJpaEntity> findByUserId(Long userId);

    @Query("""
        SELECT p FROM PersonJpaEntity p
        WHERE p.deletedAt IS NULL
          AND (
               LOWER(p.firstName) LIKE LOWER(CONCAT('%', :name, '%'))
            OR LOWER(p.lastName)  LIKE LOWER(CONCAT('%', :name, '%'))
            OR LOWER(CONCAT(p.firstName, ' ', p.lastName)) LIKE LOWER(CONCAT('%', :name, '%'))
          )
    """)
    List<PersonJpaEntity> searchByName(@Param("name") String name);
}
