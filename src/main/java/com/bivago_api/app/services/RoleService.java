package com.bivago_api.app.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bivago_api.app.dto.role.RoleRequestDTO;
import com.bivago_api.app.dto.role.RoleResponstDTO;
import com.bivago_api.domain.models.Role;
import com.bivago_api.domain.repositories.IRoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoleService {
    
    private final IRoleRepository roleR;

    @Async
    public CompletableFuture<String> create(RoleRequestDTO request) {
        Role role = new Role(
            null,
            request.name(),
            request.description()
        );

        Role saved = roleR.save(role);
        return CompletableFuture.completedFuture("Sucesso ao criar Cargo: " + saved.getId());
    }

    public CompletableFuture<List<RoleResponstDTO>> readAll() {
        return CompletableFuture.completedFuture(roleR.findAll().stream()
        .map(role -> new RoleResponstDTO(
            role.getId(),
            role.getName(),
            role.getDescription()
        )).collect(Collectors.toList()));
    }

}
