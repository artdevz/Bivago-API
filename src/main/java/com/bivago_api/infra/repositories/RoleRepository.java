package com.bivago_api.infra.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.bivago_api.domain.models.Role;
import com.bivago_api.domain.repositories.IRoleRepository;
import com.bivago_api.infra.entities.RoleEntity;
import com.bivago_api.infra.mapper.RoleEntityMapper;
import com.bivago_api.infra.repositories.jpa.RoleJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RoleRepository implements IRoleRepository {
    
    private final RoleJpaRepository jpa;

    @Override
    public List<Role> findAll() { return jpa.findAll().stream().map(RoleEntityMapper::toDomain).toList(); }

    @Override
    public Optional<Role> findById(UUID id) { return jpa.findById(id).map(RoleEntityMapper::toDomain); }

    @Override
    public Role save(Role role) {
        RoleEntity entity = RoleEntityMapper.toEntity(role);
        RoleEntity saved = jpa.save(entity);

        return RoleEntityMapper.toDomain(saved);
    }

    @Override
    public void deleteById(UUID id) { jpa.deleteById(id); }

    @Override
    public Optional<Role> findByName(String name) { return jpa.findByName(name).map(RoleEntityMapper::toDomain); }

}
