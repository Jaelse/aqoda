package com.example.aqoda.resource.hotel.repository;

import com.example.aqoda.resource.hotel.entities.GuestEntity;
import com.example.aqoda.resource.hotel.entities.KeychainEntity;
import com.example.aqoda.resource.hotel.entities.RoomEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RoomRepository extends ReactiveCrudRepository<RoomEntity, Long> {
//    Mono<RoomEntity> updateRoom(Long roomId);
//
//    Flux<RoomEntity> listAllRooms(Long hotelId);
//
//    Flux<RoomEntity> listEmptyRoom(Long hotelId);
//
//    Mono<RoomEntity> findRoomByGuestId(Long hotelId);
//
//    Mono<RoomEntity> checkIn(Long roomNo, List<GuestEntity> guests, KeychainEntity keychain);
//
//    Mono<RoomEntity> checkOut(Long roomNo, List<GuestEntity> guests, KeychainEntity keychain);
}
