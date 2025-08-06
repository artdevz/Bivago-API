package com.bivago_api.app.services;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bivago_api.app.dto.hotel.HotelRequestDTO;
import com.bivago_api.app.dto.hotel.HotelResponseDTO;
import com.bivago_api.app.dto.hotel.HotelUpdateDTO;
import com.bivago_api.app.helpers.EntityFinder;
import com.bivago_api.app.mapper.RequestMapper;
import com.bivago_api.app.mapper.ResponseMapper;
import com.bivago_api.domain.models.Hotel;
import com.bivago_api.domain.repositories.IHotelRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HotelService {
    
    private final IHotelRepository hotelR;
    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;
    private final EntityFinder finder;

    @Async
    public CompletableFuture<String> create(HotelRequestDTO request) {
        Hotel hotel = requestMapper.toHotel(request);
        Hotel saved = hotelR.save(hotel);
        return CompletableFuture.completedFuture("Sucesso ao criar hotel: " + saved.getId());
    }

    @Async
    public CompletableFuture<List<HotelResponseDTO>> readAll() { return CompletableFuture.completedFuture(responseMapper.toResponseDTOList(hotelR.findAll(), responseMapper::toHotelResponseDTO)); }

    @Async
    public CompletableFuture<HotelResponseDTO> readById(UUID id) {
        Hotel hotel = finder.findByIdOrThrow(hotelR.findById(id), "Hotel não encontrado");
        return CompletableFuture.completedFuture(responseMapper.toHotelResponseDTO(hotel));
    }

    @Async
    public CompletableFuture<String> update(UUID id, HotelUpdateDTO data) {
        Hotel hotel = finder.findByIdOrThrow(hotelR.findById(id), "Hotel não encontrado");

        hotelR.save(hotel);
        return CompletableFuture.completedFuture("Hotel atualizado");
    }

    @Async
    public CompletableFuture<String> delete(UUID id) {
        finder.findByIdOrThrow(hotelR.findById(id), "Hotel não encontrado");
        
        hotelR.deleteById(id);
        return CompletableFuture.completedFuture("Hotel deletado");
    }

}
