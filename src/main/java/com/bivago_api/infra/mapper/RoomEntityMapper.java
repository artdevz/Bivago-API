package com.bivago_api.infra.mapper;

import com.bivago_api.domain.models.Room;
import com.bivago_api.infra.entities.RoomEntity;

public class RoomEntityMapper {

    public static Room toDomain(RoomEntity entity) {
        if (entity == null) return null;
        Room room = new Room(
            entity.getId(),
            entity.getCapacity(),
            entity.getCategory(),
            entity.getNumber(),
            entity.getPrice(),
            HotelEntityMapper.toDomain(entity.getHost())
        );
        return room;
    }

    public static RoomEntity toEntity(Room room) {
        if (room == null) return null;
        RoomEntity entity = new RoomEntity();
        entity.setId(room.getId());
        entity.setCapacity(room.getCapacity());
        entity.setCategory(room.getCategory());
        entity.setNumber(entity.getNumber());
        entity.setPrice(room.getPrice());
        entity.setHost(HotelEntityMapper.toEntity(room.getHost()));

        return entity;
    }
    
}
