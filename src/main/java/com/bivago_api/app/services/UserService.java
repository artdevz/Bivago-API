package com.bivago_api.app.services;

import org.springframework.stereotype.Service;

import com.bivago_api.app.dto.UserRequestDTO;
import com.bivago_api.app.mapper.RequestMapper;
import com.bivago_api.domain.models.User;
import com.bivago_api.domain.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final IUserRepository userR;
    private final RequestMapper requestMapper;
    
    public String create(UserRequestDTO request) {
        User user = requestMapper.toUser(request);
        User saved = userR.save(user);
        return "Sucesso ao criar usuário: " + saved.getId();
    }

}
