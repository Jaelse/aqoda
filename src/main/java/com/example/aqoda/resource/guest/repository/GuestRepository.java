package com.example.aqoda.resource.guest.repository;

import com.example.aqoda.resource.guest.entities.GuestEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GuestRepository extends ReactiveCrudRepository<GuestEntity, Long> {

//    Flux<GuestEntity> listAllGuests(Long hotelId);

//    Flux<GuestEntity> listGuestsByAgeRage(Long hotelId, Integer lowerLimit, Integer upperLimit);

//    Flux<GuestEntity> listGuestsByRoom(Long hotelId, Long roomNo);
}
