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
                        .keychainNo(newGuest.getKeycardNo())
                        .roomNo(newGuest.getRoomNo())
                        .build()
                );
    }

    @Override
    public Mono<ImmutableGuest> update(UUID id, Optional<String> maybeName, Optional<Integer> maybeAge, Optional<Long> maybeRoomNo, Optional<UUID> maybeKeycardNo) {
        return guestRepository.findById(id)
                .flatMap(guest -> {
                    maybeName.ifPresent(guest::setName);
                    maybeKeycardNo.ifPresent(guest::setKeycardNo);
                    return Mono.just(guest);
                })
                .flatMap(guestRepository::save)
                .map(updatedGuest -> ImmutableGuest.builder()
                        .id(updatedGuest.getId())
                        .name(updatedGuest.getName())
                        .age(updatedGuest.getAge())
                        .keychainNo(updatedGuest.getKeycardNo())
                        .roomNo(updatedGuest.getRoomNo())
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
                        .keychainNo(guest.getKeycardNo())
                        .roomNo(guest.getRoomNo())
                        .build()
                );
    }

    @Override
    public Flux<ImmutableGuest> findByRoomNo(Long roomNo) {
        return guestRepository.findGuestEntityByRoomNo(roomNo)
                .map(guest ->
                        ImmutableGuest.builder()
                                .id(guest.getId())
                                .name(guest.getName())
                                .age(guest.getAge())
                                .keychainNo(guest.getKeycardNo())
                                .roomNo(guest.getRoomNo())
                                .build()
                );
    }
}
