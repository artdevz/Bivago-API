package com.bivago_api.app.dto.hotel;

import java.util.UUID;

import com.bivago_api.domain.models.values.Address;

public record HotelRequestDTO(

    String name,

    Address address,

    UUID owner
) {}
