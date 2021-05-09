package com.example.aqoda;

import com.example.aqoda.resource.hotel.entities.HotelEntity;
import com.example.aqoda.resource.keychain.entities.KeychainEntity;
import com.example.aqoda.resource.room.entities.RoomEntity;
import com.example.aqoda.service.hotel.HotelService;
import com.example.aqoda.service.keychain.KeychainService;
import com.example.aqoda.service.room.RoomService;
import io.r2dbc.spi.ConnectionFactory;
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
    private KeychainService keychainService;

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

            keychainService.create(KeychainEntity.builder()
                    .roomNo(201L)
                    .build())
                    .flatMap(keychain -> {
                        System.out.println(keychain.keychainNo());
                        System.out.println(keychain.roomNo());
                        return Mono.just(keychain);
                    })
                    .block();
        };
    }
}
