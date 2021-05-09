package com.example.aqoda.service.guest;

import com.example.aqoda.immutable.ImmutableGuest;
import com.example.aqoda.resource.guest.entities.GuestEntity;
import com.example.aqoda.resource.guest.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }


    @Override
    public Mono<ImmutableGuest> create(GuestEntity guest) {
        return guestRepository.save(guest)
                .map(newGuest -> ImmutableGuest.builder()
                        .id(newGuest.getId())
                        .name(newGuest.getName())
                        .age(newGuest.getAge())
                        .keychainNo(newGuest.getKeychainNo())
                        .roomNo(newGuest.getRoomNo())
                        .build()
                );
    }
}
