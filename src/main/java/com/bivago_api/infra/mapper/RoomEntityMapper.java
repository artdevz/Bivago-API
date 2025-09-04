package com.bivago_api.infra.mapper;

import com.bivago_api.domain.models.Room;
import com.bivago_api.infra.embeddables.RoomFeaturesEmbeddable;
import com.bivago_api.infra.entities.RoomEntity;

public class RoomEntityMapper {

    public static Room toDomain(RoomEntity entity, boolean details) {
        if (entity == null) return null;
        Room room = new Room(
            entity.getId(),
            entity.getCapacity(),
            entity.getCategory(),
            entity.getNumber(),
            entity.getPrice(),
            entity.getRoomFeatures().toDomain(),
            HotelEntityMapper.toDomain(entity.getHost(), false)
        );

        if (details) room.getReservations().addAll(entity.getReservations().stream().map(ReservationEntityMapper::toDomain).toList());

        return room;
    }

    public static RoomEntity toEntity(Room room) {
        if (room == null) return null;
        RoomEntity entity = new RoomEntity();
        entity.setId(room.getId());
        entity.setCapacity(room.getCapacity());
        entity.setCategory(room.getCategory());
        entity.setNumber(room.getNumber());
        entity.setPrice(room.getPrice());
        entity.setRoomFeatures(RoomFeaturesEmbeddable.fromDomain(room.getRoomFeatures()));
        entity.setHost(HotelEntityMapper.toEntity(room.getHost()));

        return entity;
    }
    
}
