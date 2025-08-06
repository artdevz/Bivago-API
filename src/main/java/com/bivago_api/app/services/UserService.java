package com.bivago_api.app.services;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bivago_api.app.dto.user.UserRequestDTO;
import com.bivago_api.app.dto.user.UserResponseDTO;
import com.bivago_api.app.dto.user.UserUpdateDTO;
import com.bivago_api.app.helpers.EntityFinder;
import com.bivago_api.app.mapper.RequestMapper;
import com.bivago_api.app.mapper.ResponseMapper;
import com.bivago_api.domain.models.User;
import com.bivago_api.domain.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userR;
    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;
    public final EntityFinder finder;
    
    @Async
    public CompletableFuture<String> create(UserRequestDTO request) {
        ensureUniqueEmail(request.email());
        User user = requestMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userR.save(user);
        return CompletableFuture.completedFuture("Sucesso ao criar usuário: " + saved.getId());
    }

    @Async
    public CompletableFuture<List<UserResponseDTO>> readAll() { return CompletableFuture.completedFuture(responseMapper.toResponseDTOList(userR.findAll(), responseMapper::toUserResponseDTO)); }

    @Async
    public CompletableFuture<UserResponseDTO> readById(UUID id) {
        User user = finder.findByIdOrThrow(userR.findById(id), "Usuário não encontrado");
        return CompletableFuture.completedFuture(responseMapper.toUserResponseDTO(user));
    }

    @Async
    public CompletableFuture<String> update(UUID id, UserUpdateDTO data) {
        User user = finder.findByIdOrThrow(userR.findById(id), "Usuário não encontrado");
        
        data.name().ifPresent(user::setName);
        data.password().ifPresent(user::setPassword);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userR.save(user);

        return CompletableFuture.completedFuture("Usuário atualizado");
    }

    @Async
    public CompletableFuture<String> delete(UUID id) {
        finder.findByIdOrThrow(userR.findById(id), "Usuário não encontrado");

        userR.deleteById(id);
        return CompletableFuture.completedFuture("Usuário deletado");
    }

    private void ensureUniqueEmail(String email) { if (userR.findByEmail(email).isPresent()) throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já está sendo utilizado"); }

}
