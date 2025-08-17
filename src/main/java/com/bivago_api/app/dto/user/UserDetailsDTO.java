package com.bivago_api.app.dto.user;

import java.util.List;

import com.bivago_api.app.dto.hotel.HotelResponseDTO;
import com.bivago_api.app.dto.reservation.ReservationResponseDTO;

public record UserDetailsDTO(
    UserResponseDTO summary,
    List<HotelResponseDTO> hotels,
    List<ReservationResponseDTO> reservations
) {}
