package com.bivago_api.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bivago_api.app.dto.reservation.ReservationRequestDTO;
import com.bivago_api.app.dto.reservation.ReservationResponseDTO;
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

    public String create(ReservationRequestDTO request) {
        Reservation reservation = requestMapper.toReservation(request);
        Reservation saved = reservationR.save(reservation);
        return "Sucesso ao criar reserva: " + saved.getId();
    }

    public List<ReservationResponseDTO> readAll() { return responseMapper.toResponseDTOList(reservationR.findAll(), responseMapper::toReservationResponseDTO); }

    public ReservationResponseDTO readById(UUID id) {
        Reservation reservation = finder.findByIdOrThrow(reservationR.findById(id), "Reserva não encontrada");
        return responseMapper.toReservationResponseDTO(reservation);
    }

    public String update(UUID id) {
        Reservation reservation = finder.findByIdOrThrow(reservationR.findById(id), "Reserva não encontrada");

        reservationR.save(reservation);
        return "Reserva atualizada";
    }

    public String delete(UUID id) {
        finder.findByIdOrThrow(reservationR.findById(id), "Reserva não encontrada");

        reservationR.deleteById(id);
        return "Reserva deletada";
    }

}
