package com.example.aqoda.immutable;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Builder
@Getter
@Accessors(fluent = true)
public class ImmutableBooking {

    private final UUID bookingId;
    private final Long roomNo;
    private final UUID guestId;
}
