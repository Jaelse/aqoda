package com.example.aqoda.service.keycard;

import com.example.aqoda.immutable.ImmutableKeycard;
import com.example.aqoda.resource.keycard.entities.KeycardEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

public interface KeycardService {

    Mono<ImmutableKeycard> findByNo(Long KeycardNo);

    Mono<ImmutableKeycard> create(KeycardEntity keychain);

    Mono<ImmutableKeycard> update(Long keycardNo, Optional<Long> maybeRoomNo, Optional<UUID> maybeGuestId);

    Mono<ImmutableKeycard> findByRoomNo(Long roomNo);

    Mono<ImmutableKeycard> findByHolderAndKeycardNo(UUID holderId, Long keycardNo);
}
