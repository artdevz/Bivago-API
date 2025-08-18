package com.bivago_api.app.dto.user;

import java.util.Set;
import java.util.UUID;

import com.bivago_api.app.dto.auth.AuthSignupDTO;

public record UserRequestDTO(
    AuthSignupDTO signup,    
    Set<UUID> roles
) {}