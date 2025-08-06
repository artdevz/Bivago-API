package com.bivago_api.app.dto.hotel;

import java.util.UUID;

import com.bivago_api.domain.models.values.Address;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record HotelRequestDTO(

    String name,

    @Min(value = 0, message = "Pontuação deve está no intervalo de 0 e 5")
    @Max(value = 5, message = "Pontuação deve está no intervalo de 0 e 5")
    float score,

    Address address,

    UUID owner
) {}
