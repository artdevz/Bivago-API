package com.bivago_api.infra.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.bivago_api.domain.models.Reservation;
import com.bivago_api.domain.repositories.IReservationRepository;
import com.bivago_api.infra.entities.ReservationEntity;
import com.bivago_api.infra.mapper.ReservationEntityMapper;
import com.bivago_api.infra.repositories.jpa.ReservationJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ReservationRepository implements IReservationRepository {
    
    private final ReservationJpaRepository jpa;

    @Override
    public List<Reservation> findAll() { return jpa.findAll().stream().map(ReservationEntityMapper::toDomain).toList(); }

    @Override
    public Optional<Reservation> findById(UUID id) { return jpa.findById(id).map(ReservationEntityMapper::toDomain); }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity entity = ReservationEntityMapper.toEntity(reservation);
        ReservationEntity saved = jpa.save(entity);

        return ReservationEntityMapper.toDomain(saved);
    }

    @Override
    public void deleteById(UUID id) { jpa.deleteById(id); }

    @Override
    public List<Reservation> findAllFiltered(UUID user) {
        return jpa.findFiltered(user).stream().map(ReservationEntityMapper::toDomain).toList();
    }

    @Override
    public List<Reservation> findByRoom(UUID room) {
        return jpa.findByRoom(room).stream().map(ReservationEntityMapper::toDomain).toList();
    }

}
