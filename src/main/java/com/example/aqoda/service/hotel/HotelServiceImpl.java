package com.example.aqoda.service.hotel;

import com.example.aqoda.immutable.ImmutableHotel;
import com.example.aqoda.resource.hotel.entities.HotelEntity;
import com.example.aqoda.resource.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    @Override
    @Transactional
    public Mono<ImmutableHotel> create(String name) {
        return hotelRepository.save(HotelEntity.builder()
                .name(name)
                .build())
                .map(hotel -> ImmutableHotel.builder()
                        .id(hotel.getId())
                        .name(hotel.getName())
                        .build()
                );
    }

    @Override
    public Mono<ImmutableHotel> findById(Long id) {
        return hotelRepository.findById(id)
                .map(hotel -> ImmutableHotel.builder()
                        .id(hotel.getId())
                        .name(hotel.getName())
                        .build()
                );
    }
}
