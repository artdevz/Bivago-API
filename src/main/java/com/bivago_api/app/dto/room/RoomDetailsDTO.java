package com.bivago_api.app.dto.room;

import java.util.List;

import com.bivago_api.app.dto.reservation.ReservationResponseDTO;

public record RoomDetailsDTO(
    RoomResponseDTO summary,
    List<ReservationResponseDTO> reservations
) {}
