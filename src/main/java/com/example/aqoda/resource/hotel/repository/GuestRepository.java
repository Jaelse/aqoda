package com.example.aqoda.resource.hotel.repository;

import com.example.aqoda.resource.hotel.entities.GuestEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface GuestRepository extends ReactiveCrudRepository<GuestEntity, Long> {

//    Flux<GuestEntity> listAllGuests(Long hotelId);

//    Flux<GuestEntity> listGuestsByAgeRage(Long hotelId, Integer lowerLimit, Integer upperLimit);

//    Flux<GuestEntity> listGuestsByRoom(Long hotelId, Long roomNo);
}
