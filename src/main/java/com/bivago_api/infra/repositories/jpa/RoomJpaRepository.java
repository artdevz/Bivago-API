package com.bivago_api.infra.repositories.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bivago_api.domain.enums.Country;
import com.bivago_api.domain.enums.RoomType;
import com.bivago_api.infra.entities.RoomEntity;

public interface RoomJpaRepository extends JpaRepository<RoomEntity, UUID> {

    @EntityGraph(attributePaths = {"reservations"})
    Optional<RoomEntity> findById(UUID id);

    @Query("""
        SELECT r FROM Room r
        JOIN r.host h
        WHERE (:country IS NULL OR :country = '' OR h.address.country = :country)
        AND (:city IS NULL OR :city = '' OR LOWER(h.address.city) LIKE LOWER(CONCAT('%', :city, '%')))
        AND (:maxPrice IS NULL OR r.price <= :maxPrice)
        AND (:maxCapacity IS NULL OR r.capacity >= :maxCapacity)
        ORDER BY h.score DESC
    """)
    List<RoomEntity> findFiltered(
        @Param("country") Country country,
        @Param("city") String city,
        @Param("maxPrice") BigDecimal maxPrice,
        @Param("maxCapacity") Byte maxCapacity
    );

    @Query("""
        SELECT r FROM Room r
        WHERE (r.host.id = :hotel)     
    """)
    List<RoomEntity> findByHotel(@Param("hotel") UUID hotel);

    @Query("""
        SELECT r FROM Room r
        WHERE (r.host.id = :hotel 
        AND r.category = :category)
        AND NOT EXISTS (
            SELECT 1 FROM Reservation res
            WHERE res.room.id = r.id
            AND res.checkIn < :checkOut
            AND res.checkOut > :checkIn
        )
    """)
    List<RoomEntity> findAvailableRooms(
        @Param("hotel") UUID hotel,
        @Param("category") RoomType category,
        @Param("checkIn") LocalDate checkIn,
        @Param("checkOut") LocalDate checkOut
    );

}
