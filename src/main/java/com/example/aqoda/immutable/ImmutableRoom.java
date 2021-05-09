package com.example.aqoda.immutable;

import com.example.aqoda.resource.hotel.entities.GuestEntity;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Builder
public class ImmutableRoom {

    private final Long roomNo;
    private final List<GuestEntity> guests;
    private final ImmutableKeychain keychain;

    public Long roomNo() {
        return roomNo;
    }

    public List<GuestEntity> guests() {
        return guests;
    }

    public Optional<ImmutableKeychain> maybeKeychain() {
        return Optional.ofNullable(keychain);
    }
}
