package com.bivago_api.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bivago_api.app.dto.UserRequestDTO;
import com.bivago_api.app.dto.UserResponseDTO;
import com.bivago_api.app.dto.UserUpdateDTO;
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
    
    public String create(UserRequestDTO request) {
        User user = requestMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userR.save(user);
        return "Sucesso ao criar usuário: " + saved.getId();
    }

    public List<UserResponseDTO> readAll() {
        return responseMapper.toResponseDTOList(userR.findAll(), responseMapper::toUserResponseDTO);
    }

    public UserResponseDTO readById(UUID id) {
        User user = finder.findByIdOrThrow(userR.findById(id), "Usuário não encontrado");
        return responseMapper.toUserResponseDTO(user);
    }

    public String update(UUID id, UserUpdateDTO data) {
        User user = finder.findByIdOrThrow(userR.findById(id), "Usuário não encontrado");
        
        data.name().ifPresent(user::setName);
        data.password().ifPresent(user::setPassword);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userR.save(user);

        return "Usuário atualizado";
    }

    public String delete(UUID id) {
        finder.findByIdOrThrow(userR.findById(id), "Usuário não encontrado");

        userR.deleteById(id);
        return "Usuário deletado";
    }

}
