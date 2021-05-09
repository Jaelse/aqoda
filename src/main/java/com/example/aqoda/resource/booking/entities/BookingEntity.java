package com.example.aqoda.resource.booking.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@Table("booking")
public class BookingEntity {

    @Id
    @Column("booking_id")
    private final UUID bookingId;

    @NonNull
    @Column("room_no")
    private final Long roomNo;

    @NonNull
    @Column("guest_id")
    private final UUID guestId;
}
