package com.example.aqoda;

import com.example.aqoda.enums.Instruction;
import com.example.aqoda.immutable.ImmutableBooking;
import com.example.aqoda.resource.booking.entities.BookingEntity;
import com.example.aqoda.resource.guest.entities.GuestEntity;
import com.example.aqoda.resource.keycard.entities.KeycardEntity;
import com.example.aqoda.resource.room.entities.RoomEntity;
import com.example.aqoda.service.booking.BookingService;
import com.example.aqoda.service.guest.GuestService;
import com.example.aqoda.service.hotel.HotelService;
import com.example.aqoda.service.keycard.KeycardService;
import com.example.aqoda.service.room.RoomService;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private KeycardService keycardService;

    @Autowired
    private GuestService guestService;

    @Autowired
    private BookingService bookingService;

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            var hotel = hotelService.create("Jaelse Hotel").block();

            List<Command> commands = getCommandsFromFileName("input.txt");

            for (Command command : commands) {
                switch (command.getName()) {
                    case create_hotel:
                        Integer noOfFloors = Integer.valueOf(command.getParams().get(0));
                        Integer noOfRooms = Integer.valueOf(command.getParams().get(1));

                        if (noOfFloors > 0 && noOfFloors < 9) {
                            if (noOfRooms > 0 && noOfRooms < 99) {
                                for (int i = 1; i <= noOfFloors; i++) {
                                    for (int j = 1; j <= noOfRooms; j++) {
                                        Long roomNo = i * 100L;
                                        roomNo = roomNo + j;

                                        var keycard = keycardService.create(KeycardEntity.builder()
                                                .build())
                                                .block();

                                        roomService.create(RoomEntity.builder()
                                                .roomNo(roomNo)
                                                .hotelId(hotel.id())
                                                .keycardNo(keycard.keycardNo())
                                                .build())
                                                .block();

                                        keycardService.update(keycard.keycardNo(), Optional.ofNullable(roomNo), Optional.empty())
                                                .block();

                                    }
                                }
                            }
                        }

                        log.info("Hotel created with {} floor(s), {} room(s) per floor.", noOfFloors, noOfRooms);
                        break;
                    case book:
                        if (command.getParams().size() == 3) {
                            Long roomNo = Long.valueOf(command.getParams().get(0));
                            String name = command.getParams().get(1);
                            Integer age = Integer.valueOf(command.getParams().get(2));

                            bookingService.findByRoomNo(roomNo)
                                    .flatMap(booking ->
                                            guestService.findById(booking.guestId())
                                                    .map(guest -> {
                                                        log.info("Cannot book room {} for {}, The room is currently booked by {}.", roomNo, name, guest.name());
                                                        return booking;
                                                    })
                                    )
                                    .switchIfEmpty(keycardService.findByRoomNo(roomNo)
                                            .map(keycard -> guestService.findByName(name)
                                                    .map(g -> keycardService.update(keycard.keycardNo(), Optional.empty(), Optional.ofNullable(g.id()))
                                                            .map(keycard1 -> bookingService.book(BookingEntity.builder()
                                                                    .guestId(g.id())
                                                                    .roomNo(roomNo)
                                                                    .build())
                                                            ).flatMap(x -> x)
                                                    )
                                                    .defaultIfEmpty(guestService.create(GuestEntity.builder()
                                                            .name(name)
                                                            .age(age)
                                                            .build())
                                                            .flatMap(guest -> keycardService.update(keycard.keycardNo(), Optional.empty(), Optional.ofNullable(guest.id()))
                                                                    .map(keycard1 -> bookingService.book(BookingEntity.builder()
                                                                            .guestId(guest.id())
                                                                            .roomNo(roomNo)
                                                                            .build())
                                                                    ).flatMap(x -> x)
                                                            )
                                                    )
                                                    .flatMap(x -> x)
                                                    .flatMap(x -> {
                                                        log.info("Room {} is booked by {} with keycard number {}.", x.roomNo(), name, keycard.keycardNo());
                                                        return Mono.just(x);
                                                    })
                                            )
                                            .flatMap(x -> x)
                                    ).block();

                        }
                        break;
                    case list_available_rooms:
                        roomService.findAll()
                                .filter(room -> bookingService.findByRoomNo(room.roomNo())
                                        .map(guest -> false)
                                        .switchIfEmpty(Mono.just(true))
                                        .block()
                                )
                                .subscribe(room -> log.info("{}", room.roomNo()));
                        break;
                    case checkout:
                        if (command.getParams().size() == 2) {
                            Long keycardNo = Long.valueOf(command.getParams().get(0));
                            String guestName = command.getParams().get(1);
                            guestService.findByName(guestName)
                                    .map(guest -> keycardService.findByHolderAndKeycardNo(guest.id(), keycardNo)
                                            .flatMap(keycard -> {
                                                        log.info("Room {} is checkout.", keycard.roomNo());
                                                        return bookingService.checkout(guest.id(), keycard.roomNo());
                                                    }
                                            )
                                            .switchIfEmpty(
                                                    keycardService.findByNo(keycardNo)
                                                            .map(keycard -> {
                                                                        return guestService.findById(keycard.guestId())
                                                                                .map(Function.identity());
                                                                    }
                                                            )
                                                            .flatMap(Function.identity())
                                                            .map(g -> {
                                                                log.info("Only {} can checkout with keycard number {}.", g.name(), keycardNo);
                                                                return ImmutableBooking.builder().build();
                                                            })
                                            )
                                    )
                                    .flatMap(Function.identity())
                                    .block();

                        }
                        break;
                    case list_guest:
                        List<String> names = bookingService.findAll()
                                .flatMap(booking -> guestService.findById(booking.guestId()))
                                .collect(Collectors.toList())
                                .map(immutableGuests -> immutableGuests.stream()
                                        .map(guest -> guest.name())
                                        .collect(Collectors.toList())
                                )
                                .block();

                        log.info("{}", names);
                        break;
                    case get_guest_in_room:
                        if (command.getParams().size() == 1) {
                            Long roomNo = Long.valueOf(command.getParams().get(0));
                            String name = bookingService.findByRoomNo(roomNo)
                                    .map(booking -> guestService.findById(booking.guestId()))
                                    .map(Function.identity())
                                    .flatMap(x -> x)
                                    .block()
                                    .name();

                            log.info("{}", name);
                        }
                    case list_guest_by_age:
                        if (command.getParams().size() == 2) {
                            String operator = command.getParams().get(0);
                            Integer age = Integer.valueOf(command.getParams().get(1));

                            if (operator == ">") {
                                List<String> n = guestService.guestsGreaterThanAge(age)
                                        .map(guest -> guest.name())
                                        .collectList()
                                        .block();

                                log.info("{}", n);
                            } else if (operator == "<") {
                                List<String> n = guestService.guestsLessThanAge(age)
                                        .map(guest -> guest.name())
                                        .collectList()
                                        .block();

                                log.info("{}", n);
                            }
                        }
                        break;
                    case list_guest_by_floor:
                        if (command.getParams().size() == 1) {
                            Integer floor = Integer.valueOf(command.getParams().get(0));
                            List<String> n = bookingService.findAll()
                                    .filter(booking -> booking.roomNo() / 100 == floor)
                                    .map(booking -> guestService.findById(booking.guestId())
                                    )
                                    .flatMap(Function.identity())
                                    .map(immutableGuest -> immutableGuest.name())
                                    .collectList()
                                    .block();

                            log.info("{}", n);
                        }
                        break;
                    case book_by_floor:
                        if (command.getParams().size() == 3) {
                            Integer floor = Integer.valueOf(command.getParams().get(0));
                            String name = command.getParams().get(1);
                            Integer age = Integer.valueOf(command.getParams().get(2));

                            Boolean canBook = true;

                            List<ImmutableBooking> bookings = roomService.findAll()
                                    .filter(room -> room.roomNo() / 100 == floor)
                                    .filter(room -> bookingService.findByRoomNo(room.roomNo()).map(r -> false).switchIfEmpty(Mono.just(true)).block())
                                    .map(room -> guestService.findByName(name)
                                            .flatMap(guest -> bookingService.book(BookingEntity.builder()
                                                            .roomNo(room.roomNo())
                                                            .guestId(guest.id())
                                                            .build()
                                                    )
                                            )
                                            .switchIfEmpty(guestService.create(GuestEntity.builder()
                                                    .name(name)
                                                    .age(age)
                                                    .build())
                                                    .flatMap(immutableGuest -> bookingService.book(BookingEntity.builder()
                                                            .roomNo(room.roomNo())
                                                            .guestId(immutableGuest.id())
                                                            .build())
                                                    )
                                            )
                                    )
                                    .map(Mono::block)
                                    .collectList()
                                    .map(ArrayList::new)
                                    .block();

                            List<Long> rooms = bookings.stream()
                                    .map(booking -> booking.roomNo())
                                    .collect(Collectors.toList());

                            List<Long> keycards = bookings.stream()
                                    .map(booking -> keycardService.findByRoomNo(booking.roomNo()))
                                    .map(immutableKeycardMono -> immutableKeycardMono.block())
                                    .map(keycard -> keycard.keycardNo())
                                    .collect(Collectors.toList());


                            log.info("{} are booked with keycard number {}.", rooms, keycards);
                        }
                        break;
                    case checkout_guest_by_floor:
                        if (command.getParams().size() == 1) {
                            Integer floor = Integer.valueOf(command.getParams().get(0));
                            List<Long> bookings = bookingService.findAll()
                                    .filter(booking -> booking.roomNo() / 100 == floor)
                                    .map(booking -> bookingService.checkout(booking.guestId(), booking.roomNo()))
                                    .flatMap(Function.identity())
                                    .map(booking -> booking.roomNo())
                                    .collectList()
                                    .block();

                            log.info("{} are checkout.", bookings);
                        }
                        break;
                }
            }
        }

                ;
    }

    private List<Command> getCommandsFromFileName(String filename) throws FileNotFoundException {
        List<Command> commands = new ArrayList();

        ClassLoader classLoader = getClass().getClassLoader();

        File myObj = new File(classLoader.getResource(filename).getFile());

        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            List<String> strings = Arrays.asList(data.split(" "));

            Instruction name = Instruction.valueOf(strings.get(0));
            List<String> params = strings.subList(1, strings.size());
            commands.add(new Command(name, params));
        }
        myReader.close();

        return commands;
    }

    @Getter
    public final static class Command {
        private final Instruction name;
        private final List<String> params;

        Command(Instruction name, List<String> params) {
            this.name = name;
            this.params = params;
        }
    }
}
