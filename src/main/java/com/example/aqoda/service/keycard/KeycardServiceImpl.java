package com.example.aqoda.service.keycard;

import com.example.aqoda.immutable.ImmutableKeycard;
import com.example.aqoda.resource.keycard.entities.KeycardEntity;
import com.example.aqoda.resource.keycard.repository.KeycardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
public class KeycardServiceImpl implements KeycardService {

    private final KeycardRepository keychainRepository;

    @Autowired
    public KeycardServiceImpl(KeycardRepository keychainRepository) {
        this.keychainRepository = keychainRepository;
    }


    @Override
    public Mono<ImmutableKeycard> create(KeycardEntity keychain) {
        return keychainRepository.save(keychain)
                .map(newKeychain -> ImmutableKeycard.builder()
                        .keychainNo(newKeychain.getKeycardNo())
                        .roomNo(newKeychain.getRoomNo())
                        .guestId(newKeychain.getGuestId())
                        .build()
                );
    }

    @Override
    public Mono<ImmutableKeycard> update(UUID keycardNo, Optional<Long> maybeRoomNo, Optional<UUID> maybeGuestId) {
        return keychainRepository.findById(keycardNo)
                .flatMap(keycard -> {
                    maybeRoomNo.ifPresent(keycard::setRoomNo);
                    maybeGuestId.ifPresent(keycard::setGuestId);
                    return Mono.just(keycard);
                })
                .flatMap(keychainRepository::save)
                .map(updatedKeycard -> ImmutableKeycard.builder()
                        .keychainNo(updatedKeycard.getKeycardNo())
                        .roomNo(updatedKeycard.getRoomNo())
                        .guestId(updatedKeycard.getGuestId())
                        .build()
                );
    }

    @Override
    public Mono<ImmutableKeycard> findByRoomNo(Long roomNo) {
        return keychainRepository.findKeycardEntityByRoomNo(roomNo)
                .map(keycard -> ImmutableKeycard.builder()
                        .keychainNo(keycard.getKeycardNo())
                        .roomNo(keycard.getRoomNo())
                        .guestId(keycard.getGuestId())
                        .build()
                );


    }
}
