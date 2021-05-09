package com.example.aqoda.resource.guest.repository;

import com.example.aqoda.resource.guest.entities.GuestEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface GuestRepository extends ReactiveCrudRepository<GuestEntity, UUID> {

    Mono<GuestEntity> findGuestEntityByName(String name);

    Flux<GuestEntity> findGuestEntityByAgeAfter(Integer age);

    Flux<GuestEntity> findGuestEntityByRoomNo(Long roomNo);
}
