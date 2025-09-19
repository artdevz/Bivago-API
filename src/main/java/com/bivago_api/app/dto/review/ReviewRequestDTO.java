package com.bivago_api.app.dto.review;

import java.util.UUID;

public record ReviewRequestDTO(
    int rating,
    String comment,
    UUID reservation,
    UUID user
) {}
