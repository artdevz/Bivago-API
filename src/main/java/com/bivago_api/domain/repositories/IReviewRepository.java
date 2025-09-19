package com.bivago_api.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bivago_api.domain.models.Review;

public interface IReviewRepository {
    
    List<Review> findAll();
    Optional<Review> findById(UUID id);
    Review save(Review review);
    void deleteById(UUID id);

    public List<Review> findFiltered(UUID user);
    public List<Review> findHotelReviews(UUID hotel);

}
