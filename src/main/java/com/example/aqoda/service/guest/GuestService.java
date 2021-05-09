package com.example.aqoda.service.guest;

import com.example.aqoda.immutable.ImmutableGuest;
import com.example.aqoda.resource.guest.entities.GuestEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GuestService {

    Mono<ImmutableGuest> create(GuestEntity guest);

    Mono<ImmutableGuest> findById(UUID guestId);

    Flux<ImmutableGuest> findAll();

    Mono<ImmutableGuest> findByName(String name);

    Flux<ImmutableGuest> guestsGreaterThanAge(Integer age);

    Flux<ImmutableGuest> guestsLessThanAge(Integer age);
}
