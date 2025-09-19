package com.bivago_api.app.services;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bivago_api.app.dto.review.ReviewRequestDTO;
import com.bivago_api.app.dto.review.ReviewResponseDTO;
import com.bivago_api.app.dto.review.ReviewUpdateDTO;
import com.bivago_api.app.helpers.EntityFinder;
import com.bivago_api.app.mapper.RequestMapper;
import com.bivago_api.app.mapper.ResponseMapper;
import com.bivago_api.domain.models.Review;
import com.bivago_api.domain.repositories.IHotelRepository;
import com.bivago_api.domain.repositories.IReviewRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {
    
    private final IReviewRepository reviewR;
    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;
    private final EntityFinder finder;

    private final IHotelRepository hotelR;

    @Transactional
    @Async
    public CompletableFuture<String> create(ReviewRequestDTO request) {
        Review review = requestMapper.toReview(request);
        Review saved = reviewR.save(review);
        hotelR.updateAvarageRating(saved.getReservation().getRoom().getHost().getId(), saved.getRating());
        return CompletableFuture.completedFuture("Sucesso ao criar avaliação: " + saved.getId());
    }

    @Async
    public CompletableFuture<List<ReviewResponseDTO>> readAll() {
        return CompletableFuture.completedFuture(responseMapper.toResponseDTOList(reviewR.findAll(), responseMapper::toReviewResponseDTO));
    }

    @Async
    public CompletableFuture<ReviewResponseDTO> readById(UUID id) {
        Review review = finder.findByIdOrThrow(reviewR.findById(id), "Avaliação não encontrada");
        return CompletableFuture.completedFuture(responseMapper.toReviewResponseDTO(review));
    }

    @Async
    public CompletableFuture<String> update(UUID id, ReviewUpdateDTO data) {
        Review review = finder.findByIdOrThrow(reviewR.findById(id), "Avaliação não encontrada");

        reviewR.save(review);
        return CompletableFuture.completedFuture("Avaliação atualizada");
    }

    @Async
    public CompletableFuture<String> delete(UUID id) {
        finder.findByIdOrThrow(reviewR.findById(id), "Avaliação não encontrada");

        reviewR.deleteById(id);
        return CompletableFuture.completedFuture("Avaliação deletada");
    }

}
