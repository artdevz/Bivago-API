package com.bivago_api.infra.repositories.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bivago_api.infra.entities.HotelEntity;

public interface HotelJpaRepository extends JpaRepository<HotelEntity, UUID> {}
