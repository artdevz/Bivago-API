package com.bivago_api.infra.mapper;

import java.util.stream.Collectors;

import com.bivago_api.domain.models.User;
import com.bivago_api.infra.entities.UserEntity;

public class UserEntityMapper {
    
    public static User toDomain(UserEntity entity, boolean details) {
        if (entity == null) return null;
        User user = new User(
            entity.getId(),
            entity.getName(),
            entity.getEmail(),
            entity.getPassword(),
            entity.getBirthday(),
            entity.getRoles().stream().map(RoleEntityMapper::toDomain).collect(Collectors.toSet())
        );

        if (details) {
            user.getHotels().addAll(entity.getHotels().stream().map(hotel -> HotelEntityMapper.toDomain(hotel, false)).toList());
            user.getReservations().addAll(entity.getReservations().stream().map(ReservationEntityMapper::toDomain).toList());
        }

        return user;
    }

    public static UserEntity toEntity(User user) {
        if (user == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setBirthday(user.getBirthday());
        entity.setRoles(user.getRoles().stream().map(RoleEntityMapper::toEntity).collect(Collectors.toSet()));

        return entity;
    }

}
