package com.example.aqoda.service.keycard;

import com.example.aqoda.immutable.ImmutableKeycard;
import com.example.aqoda.resource.keycard.entities.KeycardEntity;
import com.example.aqoda.resource.keycard.repository.KeycardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
                        .keychainNo(newKeychain.getKeychainNo())
                        .roomNo(newKeychain.getRoomNo())
                        .guestId(newKeychain.getGuestId())
                        .build()
                );
    }
}
