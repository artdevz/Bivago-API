package com.bivago_api.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bivago_api.domain.models.Reservation;

public interface IReservationRepository {
    
    List<Reservation> findAll();
    Optional<Reservation> findById(UUID id);
    Reservation save(Reservation reservation);
    void deleteById(UUID id);

}
