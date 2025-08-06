package com.bivago_api.app.dto.user;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDTO(
    UUID id,
    String name,
    String email,
    String cpf,
    LocalDate birthday,
    int role
) {}
