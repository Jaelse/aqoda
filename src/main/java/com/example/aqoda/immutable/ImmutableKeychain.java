package com.example.aqoda.immutable;

import com.example.aqoda.resource.hotel.entities.GuestEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ImmutableKeychain {

    private final Long keychainNo;
    private final ImmutableGuest holder;

    public Long keychainNo(){ return keychainNo; }

    public ImmutableGuest holder() { return holder; }
}
