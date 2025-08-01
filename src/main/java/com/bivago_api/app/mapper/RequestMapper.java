package com.bivago_api.app.mapper;

import org.springframework.stereotype.Component;

import com.bivago_api.app.dto.UserRequestDTO;
import com.bivago_api.domain.models.User;
import com.bivago_api.domain.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RequestMapper {
    
    private final IUserRepository userR;

    public User toUser(UserRequestDTO request) {
        return new User(
            null, // ID
            request.name(),
            request.email(),
            request.password(),
            request.cpf(),
            request.birthday(),
            request.role()
        );
    }

}
