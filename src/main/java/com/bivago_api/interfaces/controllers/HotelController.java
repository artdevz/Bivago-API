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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bivago_api.app.dto.hotel.HotelDetailsDTO;
import com.bivago_api.app.dto.hotel.HotelRequestDTO;
import com.bivago_api.app.dto.hotel.HotelResponseDTO;
import com.bivago_api.app.dto.hotel.HotelUpdateDTO;
import com.bivago_api.app.services.HotelService;
import com.bivago_api.shared.utils.AsyncResultHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hotel")
public class HotelController {
    
    private final HotelService hotelS;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid HotelRequestDTO request) { return ResponseEntity.status(HttpStatus.CREATED).body(AsyncResultHandler.await(hotelS.create(request))); }

    @GetMapping
    public ResponseEntity<List<HotelResponseDTO>> readAll() { return ResponseEntity.ok(AsyncResultHandler.await(hotelS.readAll())); }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDetailsDTO> readById(@PathVariable UUID id) { return ResponseEntity.ok(AsyncResultHandler.await(hotelS.readById(id))); }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody @Valid HotelUpdateDTO data) { return ResponseEntity.ok(AsyncResultHandler.await(hotelS.update(id, data))); }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) { return ResponseEntity.ok(AsyncResultHandler.await(hotelS.delete(id))); }

}
