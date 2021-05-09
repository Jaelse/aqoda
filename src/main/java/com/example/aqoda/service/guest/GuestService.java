package com.example.aqoda.service.guest;

import com.example.aqoda.immutable.ImmutableGuest;
import com.example.aqoda.resource.guest.entities.GuestEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

public interface GuestService {

    Mono<ImmutableGuest> create(GuestEntity guest);

    Mono<ImmutableGuest> update(UUID id, Optional<String> maybeName, Optional<Integer> maybeAge, Optional<Long> maybeRoomNo, Optional<UUID> maybeKeycardNo);

    Mono<ImmutableGuest> findByName(String name);

    Flux<ImmutableGuest> findByRoomNo(Long roomNo);
}
