package com.bivago_api.app.dto.reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Min;

public record ReservationRequestDTO(

    LocalDate checkIn,

    LocalDate checkOut,

    @Min(value = 1, message = "Número de Pessoas deve ser pelo menos 1")
    int nop,

    @Min(value = 0, message = "Valor não pode ser negativo")
    BigDecimal price,

    UUID guest,

    UUID room
) {}
