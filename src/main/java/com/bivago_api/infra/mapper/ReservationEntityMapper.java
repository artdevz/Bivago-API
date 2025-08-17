package com.bivago_api.infra.mapper;

import com.bivago_api.domain.models.Reservation;
import com.bivago_api.infra.entities.ReservationEntity;

public class ReservationEntityMapper {

    public static Reservation toDomain(ReservationEntity entity) {
        if (entity == null) return null;
        Reservation reservation = new Reservation(
            entity.getId(),
            entity.getCheckIn(),
            entity.getCheckOut(),
            entity.getNop(),
            UserEntityMapper.toDomain(entity.getGuest(), false),
            RoomEntityMapper.toDomain(entity.getRoom(), false)
        );
        return reservation;
    }

    public static ReservationEntity toEntity(Reservation reservation) {
        if (reservation == null) return null;
        ReservationEntity entity = new ReservationEntity();
        entity.setId(reservation.getId());
        entity.setCheckIn(reservation.getCheckIn());
        entity.setCheckOut(reservation.getCheckOut());
        entity.setNop(reservation.getNop());
        entity.setPrice(reservation.getPrice());
        entity.setGuest(UserEntityMapper.toEntity(reservation.getGuest()));
        entity.setRoom(RoomEntityMapper.toEntity(reservation.getRoom()));

        return entity;
    }
    
}
