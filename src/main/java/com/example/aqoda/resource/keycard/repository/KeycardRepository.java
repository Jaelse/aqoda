package com.example.aqoda.resource.keycard.repository;

import com.example.aqoda.resource.keycard.entities.KeycardEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface KeycardRepository extends ReactiveCrudRepository<KeycardEntity, Long> {

    Mono<KeycardEntity> findKeycardEntityByRoomNo(Long roomNo);

    Mono<KeycardEntity> findKeycardEntityByHolderAndKeycardNo(UUID holderId, Long keycardNo);
}
