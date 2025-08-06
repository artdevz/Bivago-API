package com.bivago_api.infra.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.bivago_api.domain.models.Hotel;
import com.bivago_api.domain.repositories.IHotelRepository;
import com.bivago_api.infra.entities.HotelEntity;
import com.bivago_api.infra.mapper.HotelEntityMapper;
import com.bivago_api.infra.repositories.jpa.HotelJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class HotelRepository implements IHotelRepository {
    
    private final HotelJpaRepository jpa;

    @Override
    public List<Hotel> findAll() { return jpa.findAll().stream().map(HotelEntityMapper::toDomain).toList(); }

    @Override
    public Optional<Hotel> findById(UUID id) { return jpa.findById(id).map(HotelEntityMapper::toDomain); }

    @Override
    public Hotel save(Hotel hotel) {
        HotelEntity entity = HotelEntityMapper.toEntity(hotel);
        HotelEntity saved = jpa.save(entity);

        return HotelEntityMapper.toDomain(saved);
    }

    @Override
    public void deleteById(UUID id) { jpa.deleteById(id); }
    
}
