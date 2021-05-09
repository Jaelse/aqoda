package com.example.aqoda.resource.hotel.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class KeychainEntity {

    private final Long keychainNo;
    private final GuestEntity keychainHolder;
}
