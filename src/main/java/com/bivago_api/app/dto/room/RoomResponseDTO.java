package com.bivago_api.app.dto.room;

import java.math.BigDecimal;
import java.util.UUID;

import com.bivago_api.domain.enums.Country;
import com.bivago_api.domain.enums.RoomType;
import com.bivago_api.domain.models.values.RoomFeatures;

public record RoomResponseDTO(
    UUID id,
    int capacity,
    RoomType category,
    int number,
    BigDecimal price,
    RoomFeatures roomFeatures,
    UUID host,

    // Hotel Info
    Country country,
    String division,
    String city,
    float score
) {}
