package com.bivago_api.infra.repositories.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bivago_api.infra.entities.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    
    Optional<UserEntity> findByEmail(String email);

    @EntityGraph(attributePaths = {"hotels", "reservations"})
    Optional<UserEntity> findById(UUID id);

}
