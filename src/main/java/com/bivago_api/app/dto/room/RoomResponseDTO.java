package com.bivago_api.app.dto.room;

import java.math.BigDecimal;
import java.util.UUID;

public record RoomResponseDTO(
    UUID id,
    int capacity,
    int category,
    int number,
    BigDecimal price,
    UUID host
) {}
