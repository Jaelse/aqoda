package com.example.aqoda.service.hotel;

import com.example.aqoda.immutable.ImmutableHotel;
import com.example.aqoda.resource.hotel.entities.HotelEntity;
import com.example.aqoda.resource.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

public interface HotelService {

    Mono<ImmutableHotel> create(String name);

    Mono<ImmutableHotel> findById(Long id);

}
