package com.bivago_api.infra.mapper;

import java.util.stream.Collectors;

import com.bivago_api.domain.models.User;
import com.bivago_api.infra.entities.UserEntity;

public class UserEntityMapper {
    
    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;
        User user = new User(
            entity.getId(),
            entity.getName(),
            entity.getEmail(),
            entity.getPassword(),
            entity.getCpf(),
            entity.getBirthday(),
            entity.getRoles().stream().map(RoleEntityMapper::toDomain).collect(Collectors.toSet())
        );
        return user;
    }

    public static UserEntity toEntity(User user) {
        if (user == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setCpf(user.getCPF());
        entity.setBirthday(user.getBirthday());
        entity.setRoles(user.getRoles().stream().map(RoleEntityMapper::toEntity).collect(Collectors.toSet()));

        return entity;
    }

}
