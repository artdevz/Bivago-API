package com.bivago_api.app.dto.user;

import java.util.Optional;

public record UserUpdateDTO(
    Optional<String> name,
    Optional<String> password
) {}
