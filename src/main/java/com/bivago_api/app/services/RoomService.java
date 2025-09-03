package com.bivago_api.app.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bivago_api.app.dto.room.RoomDetailsDTO;
import com.bivago_api.app.dto.room.RoomRequestDTO;
import com.bivago_api.app.dto.room.RoomResponseDTO;
import com.bivago_api.app.dto.room.RoomUpdateDTO;
import com.bivago_api.app.helpers.EntityFinder;
import com.bivago_api.app.mapper.RequestMapper;
import com.bivago_api.app.mapper.ResponseMapper;
import com.bivago_api.domain.models.Room;
import com.bivago_api.domain.repositories.IRoomRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomService {
    
    private final IRoomRepository roomR;
    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;
    private final EntityFinder finder;

    @Async
    public CompletableFuture<String> create(RoomRequestDTO request) {
        Room room = requestMapper.toRoom(request);
        Room saved = roomR.save(room);
        return CompletableFuture.completedFuture("Sucesso ao criar quarto: " + saved.getId());
    }

    @Async
    public CompletableFuture<List<RoomResponseDTO>> readAll(BigDecimal maxPrice, Byte maxCapacity) {
        return CompletableFuture.completedFuture(responseMapper.toResponseDTOList(roomR.findAllFiltered(maxPrice, maxCapacity), responseMapper::toRoomResponseDTO)); 
    }

    @Async
    public CompletableFuture<RoomDetailsDTO> readById(UUID id) {
        Room room = finder.findByIdOrThrow(roomR.findById(id), "Quarto não encontrado");
        return CompletableFuture.completedFuture(
            new RoomDetailsDTO(
                responseMapper.toRoomResponseDTO(room),
                responseMapper.toResponseDTOList(room.getReservations().stream().toList(), responseMapper::toReservationResponseDTO)
            )
        );
    }

    @Async
    public CompletableFuture<String> update(UUID id, RoomUpdateDTO data) {
        Room room = finder.findByIdOrThrow(roomR.findById(id), "Quarto não encontrado");
        roomR.save(room);
        return CompletableFuture.completedFuture("Quarto atualizado");
    }

    @Async
    public CompletableFuture<String> delete(UUID id) {
        finder.findByIdOrThrow(roomR.findById(id), "Quarto não encontrado");
        roomR.deleteById(id);
        return CompletableFuture.completedFuture("Quarto deletado");
    }

}
