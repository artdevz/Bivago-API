package com.bivago_api.infra.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.bivago_api.domain.models.Room;
import com.bivago_api.domain.repositories.IRoomRepository;
import com.bivago_api.infra.entities.RoomEntity;
import com.bivago_api.infra.mapper.RoomEntityMapper;
import com.bivago_api.infra.repositories.jpa.RoomJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RoomRepository implements IRoomRepository {
    
    private final RoomJpaRepository jpa;

    @Override
    public List<Room> findAll() { return jpa.findAll().stream().map(entity -> RoomEntityMapper.toDomain(entity, false)).toList(); }

    @Override
    public Optional<Room> findById(UUID id) { return jpa.findById(id).map(entity -> RoomEntityMapper.toDomain(entity, true)); }

    @Override
    public Room save(Room room) {
        RoomEntity entity = RoomEntityMapper.toEntity(room);
        RoomEntity saved = jpa.save(entity);

        return RoomEntityMapper.toDomain(saved, true);
    }

    @Override
    public void deleteById(UUID id) { jpa.deleteById(id); }

    @Override
    public List<Room> findAllFiltered(BigDecimal maxPrice, Byte maxCapacity) {
        return jpa.findFiltered(maxPrice, maxCapacity).stream().map(entity -> RoomEntityMapper.toDomain(entity, false)).toList();
    }

}
