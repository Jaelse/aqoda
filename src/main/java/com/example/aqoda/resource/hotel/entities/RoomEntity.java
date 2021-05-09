package com.example.aqoda.resource.hotel.entities;

import lombok.*;

import java.util.List;
import java.util.Optional;

@Setter
@Getter
@Builder
public class RoomEntity {

    @NonNull
    private Long roomNo;

    @NonNull
    private List<GuestEntity> guests;

    @NonNull
    @Getter(AccessLevel.NONE)
    private KeychainEntity keychain;

    public Optional<KeychainEntity> maybeKeychain(){
        return Optional.ofNullable(keychain);
    }
}
