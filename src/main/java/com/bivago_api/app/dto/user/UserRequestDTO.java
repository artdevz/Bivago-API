package com.bivago_api.app.dto.user;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.bivago_api.shared.utils.Password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
    String name,

    @Email(message = "Deve ter formato de email")
    String email,

    @NotBlank(message = "Senha não pode ser vazia")
    @Password
    String password,

    String cpf,
    LocalDate birthday,
    
    Set<UUID> roles
) {}