package com.example.aqoda.immutable;

import com.example.aqoda.resource.guest.entities.GuestEntity;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Builder
public class ImmutableRoom {

    private final Long roomNo;
    private final Long hotelId;

    public Long roomNo() {
        return roomNo;
    }

    public Long hotelId() {
        return hotelId;
    }
}
