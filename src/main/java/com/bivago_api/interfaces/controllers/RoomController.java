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

import com.bivago_api.app.dto.room.RoomRequestDTO;
import com.bivago_api.app.dto.room.RoomResponseDTO;
import com.bivago_api.app.dto.room.RoomUpdateDTO;
import com.bivago_api.app.services.RoomService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomS;
    
    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid RoomRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomS.create(request));
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> readAll() { return ResponseEntity.ok(roomS.readAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> readById(@PathVariable UUID id) { return ResponseEntity.ok(roomS.readById(id)); }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody @Valid RoomUpdateDTO data) {
        return ResponseEntity.ok(roomS.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) { return ResponseEntity.ok(roomS.delete(id)); }

}
