package com.example.aqoda.resource.hotel.repository;

import com.example.aqoda.resource.hotel.entities.HotelEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends ReactiveCrudRepository<HotelEntity, Long> {

//    Mono<HotelEntity> createHotel(String name, List<RoomEntity> rooms);
}
