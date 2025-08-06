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

import com.bivago_api.app.dto.user.UserRequestDTO;
import com.bivago_api.app.dto.user.UserResponseDTO;
import com.bivago_api.app.dto.user.UserUpdateDTO;
import com.bivago_api.app.services.UserService;
import com.bivago_api.shared.utils.AsyncResultHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userS;
    
    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid UserRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(AsyncResultHandler.await(userS.create(request)));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> readAll() { return ResponseEntity.ok(AsyncResultHandler.await(userS.readAll())); }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> readById(@PathVariable UUID id) { return ResponseEntity.ok(AsyncResultHandler.await(userS.readById(id))); }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody @Valid UserUpdateDTO data) {
        return ResponseEntity.ok(AsyncResultHandler.await(userS.update(id, data)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) { return ResponseEntity.ok(AsyncResultHandler.await(userS.delete(id))); }

}
