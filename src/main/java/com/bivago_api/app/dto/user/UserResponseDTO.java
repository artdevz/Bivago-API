package com.bivago_api.app.dto.user;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.bivago_api.domain.models.Role;

public record UserResponseDTO(
    UUID id,
    String name,
    String email,
    String cpf,
    LocalDate birthday,
    Set<Role> roles
) {}
