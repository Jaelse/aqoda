package com.example.aqoda;

import com.example.aqoda.resource.guest.entities.GuestEntity;
import com.example.aqoda.resource.keycard.entities.KeycardEntity;
import com.example.aqoda.resource.room.entities.RoomEntity;
import com.example.aqoda.service.guest.GuestService;
import com.example.aqoda.service.hotel.HotelService;
import com.example.aqoda.service.keycard.KeycardService;
import com.example.aqoda.service.room.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class AqodaApplication {

    private static final Logger log = LoggerFactory.getLogger(AqodaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AqodaApplication.class, args);
    }

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private KeycardService keychainService;

    @Autowired
    private GuestService guestService;


    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            var hotel = hotelService.create("Paeng's hotel").block();
            roomService.create(RoomEntity.builder()
                    .roomNo(201L)
                    .hotelId(hotel.id())
                    .build())
                    .block();


            roomService.findByRoomNo(201L)
                    .flatMap(room -> {
                        System.out.println("-----------------------");
                        System.out.println(room.roomNo());
                        System.out.println("-----------------------");
                        return Mono.just(room);
                    }).block();

            var keychain = keychainService.create(KeycardEntity.builder()
                    .roomNo(201L)
                    .build())
                    .flatMap(newKeychain -> {
                        System.out.println(newKeychain.keychainNo());
                        System.out.println(newKeychain.roomNo());
                        return Mono.just(newKeychain);
                    })
                    .block();

            guestService.create(GuestEntity.builder()
                    .name("Thor")
                    .roomNo(201L)
                    .keychainNo(keychain.keychainNo())
                    .age(24)
                    .build())
                    .flatMap(guest -> {
                        System.out.println(guest.name());
                        return Mono.just(guest);
                    }).block();
        };
    }
}
