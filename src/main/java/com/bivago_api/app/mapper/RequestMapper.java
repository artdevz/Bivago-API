package com.bivago_api.app.mapper;

import org.springframework.stereotype.Component;

import com.bivago_api.app.dto.hotel.HotelRequestDTO;
import com.bivago_api.app.dto.reservation.ReservationRequestDTO;
import com.bivago_api.app.dto.room.RoomRequestDTO;
import com.bivago_api.app.dto.user.UserRequestDTO;
import com.bivago_api.app.helpers.EntityFinder;
import com.bivago_api.domain.models.Hotel;
import com.bivago_api.domain.models.Reservation;
import com.bivago_api.domain.models.Room;
import com.bivago_api.domain.models.User;
import com.bivago_api.domain.repositories.IHotelRepository;
import com.bivago_api.domain.repositories.IRoomRepository;
import com.bivago_api.domain.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RequestMapper {
    
    private final EntityFinder finder;

    private final IUserRepository userR;
    private final IHotelRepository hotelR;
    private final IRoomRepository roomR;

    public User toUser(UserRequestDTO request) {
        return new User(
            null, // ID
            request.name(),
            request.email(),
            request.password(),
            request.cpf(),
            request.birthday(),
            request.role()
        );
    }

    public Hotel toHotel(HotelRequestDTO request) {
        return new Hotel(
            null, // ID
            request.name(),
            request.score(),
            request.address(),
            finder.findByIdOrThrow(userR.findById(request.owner()), "Usuário não encontrado")
        );
    }

    public Room toRoom(RoomRequestDTO request) {
        return new Room(
            null, // ID
            request.capacity(),
            request.category(),
            request.number(),
            request.price(),
            finder.findByIdOrThrow(hotelR.findById(request.host()), "Hotel não encontrado")
        );
    }

    public Reservation toReservation(ReservationRequestDTO request) {
        return new Reservation(
            null, // ID
            request.checkIn(),
            request.checkOut(),
            request.nop(),
            request.price(),
            finder.findByIdOrThrow(userR.findById(request.guest()), "Usuário não encontrado"),
            finder.findByIdOrThrow(roomR.findById(request.room()), "Quarto não encontrado")
        );
    }

}
