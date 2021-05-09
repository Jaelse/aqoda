package com.example.aqoda.service.booking;

import com.example.aqoda.immutable.ImmutableBooking;
import com.example.aqoda.resource.booking.entities.BookingEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BookingService {

    Mono<ImmutableBooking> book(BookingEntity booking);

    Mono<ImmutableBooking> checkout(UUID guestId, Long RoomNo);

    Mono<ImmutableBooking> findByRoomNo(Long roomNo);

    Flux<ImmutableBooking> findByGuestId(UUID guestId);

    Flux<ImmutableBooking> findAll();
}
