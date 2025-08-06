package com.bivago_api.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bivago_api.app.dto.room.RoomRequestDTO;
import com.bivago_api.app.dto.room.RoomResponseDTO;
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

    public String create(RoomRequestDTO request) {
        Room room = requestMapper.toRoom(request);
        Room saved = roomR.save(room);
        return "Sucesso ao criar quarto: " + saved.getId();
    }

    public List<RoomResponseDTO> readAll() { return responseMapper.toResponseDTOList(roomR.findAll(), responseMapper::toRoomResponseDTO); }

    public RoomResponseDTO readById(UUID id) {
        Room room = finder.findByIdOrThrow(roomR.findById(id), "Quarto não encontrado");
        return responseMapper.toRoomResponseDTO(room);
    }

    public String update(UUID id) {
        Room room = finder.findByIdOrThrow(roomR.findById(id), "Quarto não encontrado");
        roomR.save(room);
        return "Quarto atualizado";
    }

    public String delete(UUID id) {
        finder.findByIdOrThrow(roomR.findById(id), "Quarto não encontrado");
        roomR.deleteById(id);
        return "Quarto deletado";
    }

}
