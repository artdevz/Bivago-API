package com.bivago_api.infra.repositories.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bivago_api.infra.entities.ReviewEntity;

public interface ReviewJpaRepository extends JpaRepository<ReviewEntity, UUID> {}
