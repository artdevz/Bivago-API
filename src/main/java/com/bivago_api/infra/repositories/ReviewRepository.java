package com.bivago_api.infra.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.bivago_api.domain.models.Review;
import com.bivago_api.domain.repositories.IReviewRepository;
import com.bivago_api.infra.entities.ReviewEntity;
import com.bivago_api.infra.mapper.ReviewEntityMapper;
import com.bivago_api.infra.repositories.jpa.ReviewJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ReviewRepository implements IReviewRepository {
    
    private final ReviewJpaRepository jpa;

    @Override
    public List<Review> findAll() { return jpa.findAll().stream().map(ReviewEntityMapper::toDomain).toList(); }

    @Override
    public Optional<Review> findById(UUID id) { return jpa.findById(id).map(ReviewEntityMapper::toDomain); }

    @Override
    public Review save(Review review) {
        ReviewEntity entity = ReviewEntityMapper.toEntity(review);
        ReviewEntity saved = jpa.save(entity);

        return ReviewEntityMapper.toDomain(saved);
    }

    @Override
    public void deleteById(UUID id) { jpa.deleteById(id); }

    @Override
    public List<Review> findFiltered(UUID user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findFiltered'");
    }

    @Override
    public List<Review> findHotelReviews(UUID hotel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findHotelReviews'");
    }
        
}
