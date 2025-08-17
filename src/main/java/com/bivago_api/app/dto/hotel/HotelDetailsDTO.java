package com.bivago_api.app.dto.hotel;

import java.util.List;

import com.bivago_api.app.dto.room.RoomResponseDTO;

public record HotelDetailsDTO(
    HotelResponseDTO summary,
    List<RoomResponseDTO> rooms
) {}
