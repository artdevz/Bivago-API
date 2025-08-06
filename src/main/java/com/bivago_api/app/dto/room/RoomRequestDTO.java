package com.bivago_api.app.dto.room;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.Min;

public record RoomRequestDTO(

    @Min(value = 1, message = "Quarto deve ter no mínimo 1 de capacidade")
    int capacity,
    
    int category,

    @Min(value = 1, message = "Número do Quarto deve ser positivo absoluto")
    int number,
    
    @Min(value = 0, message = "Valor não pode ser negativo")
    BigDecimal price,

    UUID host
) {}
