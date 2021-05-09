package com.example.aqoda.resource.room.entities;

import com.example.aqoda.resource.hotel.entities.HotelEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Setter
@Getter
@Builder
@Table("room")
public class RoomEntity implements Persistable<Long> {

    @NonNull
    @Id
    @Column("room_no")
    private Long roomNo;

    @NonNull
    @Column("hotel_id")
    private Long hotelId;

    @Override
    public Long getId() {
        return roomNo;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
