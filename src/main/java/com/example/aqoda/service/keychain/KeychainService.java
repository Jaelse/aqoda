package com.example.aqoda.service.keychain;

import com.example.aqoda.immutable.ImmutableHotel;
import com.example.aqoda.immutable.ImmutableKeychain;
import com.example.aqoda.resource.keychain.entities.KeychainEntity;
import reactor.core.publisher.Mono;

public interface KeychainService {

    Mono<ImmutableKeychain> create(KeychainEntity keychain);
}
