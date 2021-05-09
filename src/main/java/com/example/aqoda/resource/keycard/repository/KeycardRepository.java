package com.example.aqoda.resource.keycard.repository;

import com.example.aqoda.resource.keycard.entities.KeycardEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeycardRepository extends ReactiveCrudRepository<KeycardEntity, Long> {
}
