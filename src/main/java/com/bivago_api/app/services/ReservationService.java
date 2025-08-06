package com.bivago_api.app.services;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bivago_api.app.dto.reservation.ReservationRequestDTO;
import com.bivago_api.app.dto.reservation.ReservationResponseDTO;
import com.bivago_api.app.dto.reservation.ReservationUpdateDTO;
import com.bivago_api.app.helpers.EntityFinder;
import com.bivago_api.app.mapper.RequestMapper;
import com.bivago_api.app.mapper.ResponseMapper;
import com.bivago_api.domain.models.Reservation;
import com.bivago_api.domain.repositories.IReservationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationService {
    
    private final IReservationRepository reservationR;
    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;
    private final EntityFinder finder;

    @Async
    public CompletableFuture<String> create(ReservationRequestDTO request) {
        Reservation reservation = requestMapper.toReservation(request);
        Reservation saved = reservationR.save(reservation);
        return CompletableFuture.completedFuture("Sucesso ao criar reserva: " + saved.getId());
    }

    @Async
    public CompletableFuture<List<ReservationResponseDTO>> readAll() { return CompletableFuture.completedFuture(responseMapper.toResponseDTOList(reservationR.findAll(), responseMapper::toReservationResponseDTO)); }

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

}
