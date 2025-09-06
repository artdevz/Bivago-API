package com.bivago_api.app.dto.reservation;

import java.time.LocalDate;
import java.util.UUID;

import com.bivago_api.domain.enums.RoomType;

import jakarta.validation.constraints.Min;

public record ReservationRequestDTO(

    LocalDate checkIn,

    LocalDate checkOut,

    @Min(value = 1, message = "Número de Pessoas deve ser pelo menos 1")
    int nop,

    UUID guest,
    
    RoomType roomType,
    
    UUID hotel
) {}
