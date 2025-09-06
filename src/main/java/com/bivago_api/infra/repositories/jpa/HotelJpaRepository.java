package com.bivago_api.infra.repositories.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bivago_api.infra.entities.HotelEntity;

public interface HotelJpaRepository extends JpaRepository<HotelEntity, UUID> {

    @EntityGraph(attributePaths = {"rooms"})
    Optional<HotelEntity> findById(UUID id);

    @Query("""
        SELECT h FROM Hotel h
        WHERE (:user IS NULL OR h.owner.id = :user)     
    """)
    List<HotelEntity> findFiltered(
        @Param("user") UUID user
    );

}
