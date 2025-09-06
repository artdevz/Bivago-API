package com.bivago_api.infra.repositories.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bivago_api.infra.entities.ReservationEntity;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, UUID> {

    @Query("""
        SELECT r FROM Reservation r
        WHERE (:user IS NULL OR r.guest.id = :user)
    """)
    List<ReservationEntity> findFiltered(
        @Param("user") UUID user
    );

    @Query("""
        SELECT r FROM Reservation r
        WHERE (r.room.id = :room)
    """)
    List<ReservationEntity> findByRoom(
        @Param("room") UUID room
    );

}
