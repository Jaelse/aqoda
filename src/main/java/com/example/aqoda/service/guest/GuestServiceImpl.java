package com.example.aqoda.service.guest;

import com.example.aqoda.immutable.ImmutableGuest;
import com.example.aqoda.resource.guest.entities.GuestEntity;
import com.example.aqoda.resource.guest.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

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
                        .build()
                );
    }

    @Override
    public Mono<ImmutableGuest> findById(UUID guestId) {

        return guestRepository.findById(guestId)
                .map(guest -> ImmutableGuest.builder()
                        .id(guest.getId())
                        .name(guest.getName())
                        .age(guest.getAge())
                        .build()
                );
    }

    @Override
    public Flux<ImmutableGuest> findAll() {
        return guestRepository.findAll()
                .map(guest -> ImmutableGuest.builder()
                        .id(guest.getId())
                        .name(guest.getName())
                        .age(guest.getAge())
                        .build()
                );
    }

    @Override
    public Mono<ImmutableGuest> findByName(String name) {
        return guestRepository.findGuestEntityByName(name)
                .map(guest -> ImmutableGuest.builder()
                        .id(guest.getId())
                        .name(guest.getName())
                        .age(guest.getAge())
                        .build()
                );
    }

    @Override
    public Flux<ImmutableGuest> guestsGreaterThanAge(Integer age) {
        return guestRepository.findGuestEntityByAgeAfter(age)
                .map(guest -> ImmutableGuest.builder()
                        .id(guest.getId())
                        .name(guest.getName())
                        .age(guest.getAge())
                        .build()
                );

    }

    @Override
    public Flux<ImmutableGuest> guestsLessThanAge(Integer age) {
        return guestRepository.findGuestEntityByAgeBefore(age)
                .map(guest -> ImmutableGuest.builder()
                        .id(guest.getId())
                        .name(guest.getName())
                        .age(guest.getAge())
                        .build()
                );
    }
}
