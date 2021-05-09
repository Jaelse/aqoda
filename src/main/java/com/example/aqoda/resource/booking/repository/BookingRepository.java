package com.example.aqoda.resource.booking.repository;

import com.example.aqoda.resource.booking.entities.BookingEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface BookingRepository extends ReactiveCrudRepository<BookingEntity, UUID> {

    Flux<BookingEntity> findBookingEntityByGuestId(UUID guestId);

    Mono<BookingEntity> findBookingEntityByGuestIdAndAndRoomNo(UUID guestId, Long roomNo);

    Mono<BookingEntity> findByRoomNo(Long roomNo);
}
