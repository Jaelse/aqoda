package com.example.aqoda;

import com.example.aqoda.service.hotel.HotelService;
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


    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            hotelService.create("Paeng's hotel")
                    .flatMap(hotel -> {
                        System.out.println("--------");
                        System.out.println(hotel.id());
                        System.out.println(hotel.name());
                        System.out.println("--------");
                        return Mono.just(hotel);
                    }).block();

            hotelService.findById(1L)
                    .flatMap(hotel -> {
                        System.out.println("--------");
                        System.out.println(hotel.id());
                        System.out.println(hotel.name());
                        System.out.println("--------");
                        return Mono.just(hotel);
                    }).block();
        };
    }

}
