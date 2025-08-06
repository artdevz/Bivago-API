package com.bivago_api.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bivago_api.app.dto.hotel.HotelRequestDTO;
import com.bivago_api.app.dto.hotel.HotelResponseDTO;
import com.bivago_api.app.helpers.EntityFinder;
import com.bivago_api.app.mapper.RequestMapper;
import com.bivago_api.app.mapper.ResponseMapper;
import com.bivago_api.domain.models.Hotel;
import com.bivago_api.domain.repositories.IHotelRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HotelService {
    
    private final IHotelRepository hotelR;
    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;
    private final EntityFinder finder;

    public String create(HotelRequestDTO request) {
        Hotel hotel = requestMapper.toHotel(request);
        Hotel saved = hotelR.save(hotel);
        return "Sucesso ao criar hotel: " + saved.getId();
    }

    public List<HotelResponseDTO> readAll() { return responseMapper.toResponseDTOList(hotelR.findAll(), responseMapper::toHotelResponseDTO); }

    public HotelResponseDTO readById(UUID id) {
        Hotel hotel = finder.findByIdOrThrow(hotelR.findById(id), "Hotel não encontrado");
        return responseMapper.toHotelResponseDTO(hotel);
    }

    public String update(UUID id) {
        Hotel hotel = finder.findByIdOrThrow(hotelR.findById(id), "Hotel não encontrado");

        hotelR.save(hotel);
        return "Hotel atualizado";
    }

    public String delete(UUID id) {
        finder.findByIdOrThrow(hotelR.findById(id), "Hotel não encontrado");
        
        hotelR.deleteById(id);
        return "Hotel deletado";
    }

}
