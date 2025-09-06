package com.bivago_api.domain.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bivago_api.domain.enums.Country;
import com.bivago_api.domain.enums.RoomType;
import com.bivago_api.domain.models.Room;

public interface IRoomRepository {
    
    List<Room> findAll();
    Optional<Room> findById(UUID id);
    Room save(Room room);
    void deleteById(UUID id);

    List<Room> findAllFiltered(Country country, String city, BigDecimal maxPrice, Byte maxCapacity);
    List<Room> findByHotel(UUID hotel);
    public List<Room> findAvailableRooms(UUID hotel, RoomType category, LocalDate checkIn, LocalDate checkOut);

}
