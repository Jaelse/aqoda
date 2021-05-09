package com.example.aqoda.immutable;

import lombok.Builder;

@Builder
public class ImmutableKeychain {

    private final Long keychainNo;
    private final ImmutableGuest holder;

    public Long keychainNo(){ return keychainNo; }

    public ImmutableGuest holder() { return holder; }
}
