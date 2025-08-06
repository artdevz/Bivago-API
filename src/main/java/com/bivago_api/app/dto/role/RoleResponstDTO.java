package com.bivago_api.app.dto.role;

import java.util.UUID;

public record RoleResponstDTO(
    UUID id,
    String name,
    String description
) {}
