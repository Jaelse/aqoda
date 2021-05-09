package com.example.aqoda.service.room;

import com.example.aqoda.immutable.ImmutableRoom;
import com.example.aqoda.resource.room.entities.RoomEntity;
import com.example.aqoda.resource.room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Mono<ImmutableRoom> create(RoomEntity room) {
        return roomRepository.save(room)
                .map(newRoom -> ImmutableRoom
                        .builder()
                        .roomNo(newRoom.getRoomNo())
                        .hotelId(newRoom.getHotelId())
                        .build());
    }

    @Override
    public Mono<ImmutableRoom> findByRoomNo(Long roomNo) {
        return roomRepository.findById(roomNo)
                .map(room -> ImmutableRoom
                        .builder()
                        .roomNo(room.getRoomNo())
                        .hotelId(room.getHotelId())
                        .build());
    }

    @Override
    public Flux<ImmutableRoom> findAll() {
        return roomRepository.findAll()
                .map(room -> ImmutableRoom
                                .builder()
                                .roomNo(room.getRoomNo())
                        .hotelId(room.getHotelId())
                                .build()
                );
    }
}
