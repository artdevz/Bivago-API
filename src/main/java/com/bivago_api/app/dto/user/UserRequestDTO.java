package com.bivago_api.app.dto.user;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.Email;

public record UserRequestDTO(
    String name,

    @Email(message = "Deve ter formato de email")
    String email,

    String password,
    String cpf,
    LocalDate birthday,
    
    Set<UUID> roles
) {}