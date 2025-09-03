package com.bivago_api.domain.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bivago_api.domain.models.Room;

public interface IRoomRepository {
    
    List<Room> findAll();
    Optional<Room> findById(UUID id);
    Room save(Room room);
    void deleteById(UUID id);

    List<Room> findAllFiltered(BigDecimal maxPrice, Byte maxCapacity);

}
