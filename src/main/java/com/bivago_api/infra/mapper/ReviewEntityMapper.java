package com.bivago_api.infra.mapper;

import com.bivago_api.domain.models.Review;
import com.bivago_api.infra.entities.ReviewEntity;

public class ReviewEntityMapper {
    
    public static Review toDomain(ReviewEntity entity) {
        if (entity == null) return null;
        Review review = new Review(
            entity.getId(),
            entity.getRating(),
            entity.getComment(),
            ReservationEntityMapper.toDomain(entity.getReservation()),
            UserEntityMapper.toDomain(entity.getUser(), false)
        );

        return review;
    }

    public static ReviewEntity toEntity(Review review) {
        if (review == null) return null;
        ReviewEntity entity = new ReviewEntity();
        entity.setId(review.getId());
        entity.setRating(review.getRating());
        entity.setComment(review.getComment());
        entity.setReservation(ReservationEntityMapper.toEntity(review.getReservation()));
        entity.setUser(UserEntityMapper.toEntity(review.getUser()));

        return entity;
    }

}
