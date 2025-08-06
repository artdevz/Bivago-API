package com.bivago_api.app.dto.reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ReservationRequestDTO(
    LocalDate checkIn,
    LocalDate checkOut,
    int nop,
    BigDecimal price,
    UUID guest,
    UUID room
) {}
