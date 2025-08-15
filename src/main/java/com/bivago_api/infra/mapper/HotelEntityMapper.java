package com.bivago_api.infra.mapper;

import com.bivago_api.domain.models.Hotel;
import com.bivago_api.infra.embeddables.AddressEmbeddable;
import com.bivago_api.infra.entities.HotelEntity;

public class HotelEntityMapper {
    
    public static Hotel toDomain(HotelEntity entity) {
        if (entity == null) return null;
        System.out.println("HotelE: " + entity.getAddress().getStreet());
        Hotel hotel = new Hotel(
            entity.getId(),
            entity.getName(),
            entity.getScore(),
            entity.getAddress().toDomain(),
            UserEntityMapper.toDomain(entity.getOwner())
        );
        System.out.println("HotelD: " + hotel.getAddress().getStreet());
        return hotel;
    }

    public static HotelEntity toEntity(Hotel hotel) {
        if (hotel == null) return null;
        HotelEntity entity = new HotelEntity();
        entity.setId(hotel.getId());
        entity.setName(hotel.getName());
        entity.setScore(hotel.getScore());
        entity.setAddress(AddressEmbeddable.fromDomain(hotel.getAddress()));
        entity.setOwner(UserEntityMapper.toEntity(hotel.getOwner()));

        return entity;
    }

}
