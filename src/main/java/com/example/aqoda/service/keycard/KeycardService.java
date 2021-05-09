package com.example.aqoda.service.keycard;

import com.example.aqoda.immutable.ImmutableKeycard;
import com.example.aqoda.resource.keycard.entities.KeycardEntity;
import reactor.core.publisher.Mono;

public interface KeycardService {

    Mono<ImmutableKeycard> create(KeycardEntity keychain);
}
