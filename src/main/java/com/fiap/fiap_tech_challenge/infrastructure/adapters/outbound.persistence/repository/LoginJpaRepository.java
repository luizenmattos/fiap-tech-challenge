package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.LoginJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginJpaRepository extends JpaRepository<LoginJpaEntity,Long > {


}
