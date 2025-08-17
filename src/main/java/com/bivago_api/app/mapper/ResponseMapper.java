package com.bivago_api.app.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bivago_api.app.dto.hotel.HotelResponseDTO;
import com.bivago_api.app.dto.reservation.ReservationResponseDTO;
import com.bivago_api.app.dto.room.RoomResponseDTO;
import com.bivago_api.app.dto.user.UserResponseDTO;
import com.bivago_api.domain.models.Hotel;
import com.bivago_api.domain.models.Reservation;
import com.bivago_api.domain.models.Room;
import com.bivago_api.domain.models.User;

@Component
public class ResponseMapper {

    public <T, R> List<R> toResponseDTOList(List<T> entities, Function<T, R> mapper) {
        return entities.stream().map(mapper).collect(Collectors.toList());
    }
    
    public UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getCPF(),
            user.getBirthday(),
            user.getRoles()
        );
    }

    public HotelResponseDTO toHotelResponseDTO(Hotel hotel) {
        return new HotelResponseDTO(
            hotel.getId(),
            hotel.getName(),
            hotel.getScore(),
            hotel.getAddress(),
            hotel.getOwner().getId()
        );
    }

    public RoomResponseDTO toRoomResponseDTO(Room room) {
        return new RoomResponseDTO(
            room.getId(),
            room.getCapacity(),
            room.getCategory(),
            room.getNumber(),
            room.getPrice(),
            room.getHost().getId()
        );
    }

    public ReservationResponseDTO toReservationResponseDTO(Reservation reservation) {
        return new ReservationResponseDTO(
            reservation.getId(),
            reservation.getCheckIn(),
            reservation.getCheckOut(),
            reservation.getNop(),
            reservation.getPrice(),
            reservation.getGuest().getId(),
            reservation.getRoom().getId()
        );
    }

}
