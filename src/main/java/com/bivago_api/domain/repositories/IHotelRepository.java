package com.bivago_api.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bivago_api.domain.models.Hotel;

public interface IHotelRepository {
    
    List<Hotel> findAll();
    Optional<Hotel> findById(UUID id);
    Hotel save(Hotel hotel);
    void deleteById(UUID id);

    public List<Hotel> findFiltered(UUID user);

}
