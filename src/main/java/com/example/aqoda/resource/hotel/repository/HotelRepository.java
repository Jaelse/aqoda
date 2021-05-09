package com.example.aqoda.resource.hotel.repository;

import com.example.aqoda.resource.hotel.entities.GuestEntity;
import com.example.aqoda.resource.hotel.entities.HotelEntity;
import com.example.aqoda.resource.hotel.entities.KeychainEntity;
import com.example.aqoda.resource.hotel.entities.RoomEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface HotelRepository extends ReactiveCrudRepository<HotelEntity, Long> {

//    Mono<HotelEntity> createHotel(String name, List<RoomEntity> rooms);
}
