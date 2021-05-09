package com.example.aqoda.service.guest;

import com.example.aqoda.immutable.ImmutableGuest;
import com.example.aqoda.resource.guest.entities.GuestEntity;
import reactor.core.publisher.Mono;

public interface GuestService {

    Mono<ImmutableGuest> create(GuestEntity guest);
}
