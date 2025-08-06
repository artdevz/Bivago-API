package com.bivago_api.app.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bivago_api.app.dto.user.UserResponseDTO;
import com.bivago_api.domain.models.User;

@Component
public class ResponseMapper {

    public <T, R> List<R> toResponseDTOList(List<T> entities, Function<T, R> mapper) {
        return entities.stream().map(mapper).collect(Collectors.toList());
    }
    
    public UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getCPF(),
            user.getBirthday(),
            user.getRole()
        );
    }

}
