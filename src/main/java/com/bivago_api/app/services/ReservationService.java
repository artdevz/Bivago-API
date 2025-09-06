package com.bivago_api.app.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bivago_api.app.dto.reservation.ReservationRequestDTO;
import com.bivago_api.app.dto.reservation.ReservationResponseDTO;
import com.bivago_api.app.dto.reservation.ReservationUpdateDTO;
import com.bivago_api.app.helpers.EntityFinder;
import com.bivago_api.app.mapper.RequestMapper;
import com.bivago_api.app.mapper.ResponseMapper;
import com.bivago_api.domain.models.Reservation;
import com.bivago_api.domain.models.Room;
import com.bivago_api.domain.repositories.IReservationRepository;
import com.bivago_api.domain.repositories.IRoomRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationService {
    
    private final IReservationRepository reservationR;
    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;
    private final EntityFinder finder;

    private final IRoomRepository roomR;

    @Async
    public CompletableFuture<String> create(ReservationRequestDTO request) {
        ensureCheckInIsBeforeCheckOut(request.checkIn(), request.checkOut());

        List<Room> availableRooms = roomR.findAvailableRooms(request.hotel(), request.roomType(), request.checkIn(), request.checkOut());
        Room roomToReserve = availableRooms.stream().findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Nenhum quarto disponível"));

        Reservation reservation = requestMapper.toReservation(request, roomToReserve);
        Reservation saved = reservationR.save(reservation);
        return CompletableFuture.completedFuture("Sucesso ao criar reserva: " + saved.getId());
    }

    @Async
    public CompletableFuture<List<ReservationResponseDTO>> readAll(UUID user) { 
        return CompletableFuture.completedFuture(responseMapper.toResponseDTOList(reservationR.findAllFiltered(user), responseMapper::toReservationResponseDTO)); 
    }

    @Async
    public CompletableFuture<ReservationResponseDTO> readById(UUID id) {
        Reservation reservation = finder.findByIdOrThrow(reservationR.findById(id), "Reserva não encontrada");
        return CompletableFuture.completedFuture(responseMapper.toReservationResponseDTO(reservation));
    }

    @Async
    public CompletableFuture<String> update(UUID id, ReservationUpdateDTO data) {
        Reservation reservation = finder.findByIdOrThrow(reservationR.findById(id), "Reserva não encontrada");

        reservationR.save(reservation);
        return CompletableFuture.completedFuture("Reserva atualizada");
    }

    @Async
    public CompletableFuture<String> delete(UUID id) {
        finder.findByIdOrThrow(reservationR.findById(id), "Reserva não encontrada");

        reservationR.deleteById(id);
        return CompletableFuture.completedFuture("Reserva deletada");
    }

    private void ensureCheckInIsBeforeCheckOut(LocalDate checkIn, LocalDate checkOut) {
        if (checkOut.isBefore(checkIn)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Check-In deve ser antes de Check-Out");
    }

    // private boolean isAvailable(UUID id, LocalDate checkIn, LocalDate checkOut) {
    //     List<Reservation> reservations = reservationR.findByRoom(id);
    //     for (Reservation reservation : reservations) {
    //         if (reservation.getCheckIn().isBefore(checkOut) && reservation.getCheckOut().isAfter(checkIn)) return false;
    //     }
    //     return true;
    // }

}
