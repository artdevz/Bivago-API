package com.bivago_api.infra.repositories.jpa;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bivago_api.infra.entities.RoomEntity;

public interface RoomJpaRepository extends JpaRepository<RoomEntity, UUID> {

    @EntityGraph(attributePaths = {"reservations"})
    Optional<RoomEntity> findById(UUID id);

    @Query("""
        SELECT r FROM Room r
        WHERE (:maxPrice IS NULL OR r.price <= :maxPrice)
        AND (:maxCapacity IS NULL OR r.capacity >= :maxCapacity)
    """)
    List<RoomEntity> findFiltered(
        @Param("maxPrice") BigDecimal maxPrice,
        @Param("maxCapacity") Byte maxCapacity
    );

}
