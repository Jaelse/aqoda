package com.example.aqoda.resource.keychain.repository;

import com.example.aqoda.resource.keychain.entities.KeychainEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeychainRepository extends ReactiveCrudRepository<KeychainEntity, Long> {
}
