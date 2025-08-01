package com.bivago_api.app.dto;

import java.time.LocalDate;

public record UserRequestDTO(
    String name,
    String email,
    String password,
    String cpf,
    LocalDate birthday,
    int role
) {}