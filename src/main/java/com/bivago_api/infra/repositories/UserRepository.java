package com.bivago_api.infra.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.bivago_api.domain.models.User;
import com.bivago_api.domain.repositories.IUserRepository;
import com.bivago_api.infra.entities.UserEntity;
import com.bivago_api.infra.mapper.UserEntityMapper;
import com.bivago_api.infra.repositories.jpa.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepository implements IUserRepository {
    
    private final UserJpaRepository jpa;

    @Override
    public List<User> findAll() { return jpa.findAll().stream().map(entity -> UserEntityMapper.toDomain(entity, false)).toList(); }

    @Override
    public Optional<User> findById(UUID id) { return jpa.findById(id).map(entity -> UserEntityMapper.toDomain(entity, true)); }

    @Override
    public User save(User user) {
        UserEntity entity = UserEntityMapper.toEntity(user);
        UserEntity saved = jpa.save(entity);

        return UserEntityMapper.toDomain(saved, true);
    }

    @Override
    public void deleteById(UUID id) { jpa.deleteById(id); }

    @Override
    public Optional<User> findByEmail(String email) { return jpa.findByEmail(email).map(entity -> UserEntityMapper.toDomain(entity, true)); }

    @Override
    public Optional<User> findByCpf(String cpf) { return jpa.findByCpf(cpf).map(entity -> UserEntityMapper.toDomain(entity, true)); }

}
