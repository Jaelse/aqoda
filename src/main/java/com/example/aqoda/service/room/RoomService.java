package com.example.aqoda.service.room;

import com.example.aqoda.immutable.ImmutableRoom;
import com.example.aqoda.resource.room.entities.RoomEntity;
import reactor.core.publisher.Mono;

public interface RoomService {

    Mono<ImmutableRoom> create(RoomEntity room);
    Mono<ImmutableRoom> findByRoomNo(Long roomNo);

}
