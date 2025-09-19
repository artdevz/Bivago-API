package com.bivago_api.interfaces.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bivago_api.app.dto.review.ReviewRequestDTO;
import com.bivago_api.app.dto.review.ReviewResponseDTO;
import com.bivago_api.app.dto.review.ReviewUpdateDTO;
import com.bivago_api.app.services.ReviewService;
import com.bivago_api.shared.utils.AsyncResultHandler;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    
    private final ReviewService reviewS;

    @PostMapping
    public ResponseEntity<String> create(ReviewRequestDTO request) { return ResponseEntity.status(HttpStatus.CREATED).body(AsyncResultHandler.await(reviewS.create(request))); }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> readAll() { return ResponseEntity.ok(AsyncResultHandler.await(reviewS.readAll())); }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> readById(@PathVariable UUID id) { return ResponseEntity.ok(AsyncResultHandler.await(reviewS.readById(id))); }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody @Valid ReviewUpdateDTO data) { return ResponseEntity.ok(AsyncResultHandler.await(reviewS.update(id, data))); }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) { return ResponseEntity.ok(AsyncResultHandler.await(reviewS.delete(id))); }

}
