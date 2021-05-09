package com.example.aqoda.service.keychain;

import com.example.aqoda.immutable.ImmutableKeychain;
import com.example.aqoda.resource.keychain.entities.KeychainEntity;
import com.example.aqoda.resource.keychain.repository.KeychainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class KeychainServiceImpl implements KeychainService {

    private final KeychainRepository keychainRepository;

    @Autowired
    public KeychainServiceImpl(KeychainRepository keychainRepository) {
        this.keychainRepository = keychainRepository;
    }


    @Override
    public Mono<ImmutableKeychain> create(KeychainEntity keychain) {
        return keychainRepository.save(keychain)
                .map(newKeychain -> ImmutableKeychain.builder()
                        .keychainNo(newKeychain.getKeychainNo())
                        .roomNo(newKeychain.getRoomNo())
                        .build()
                );
    }
}
