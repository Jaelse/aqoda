package com.example.aqoda.service.booking;

import com.example.aqoda.immutable.ImmutableBooking;
import com.example.aqoda.immutable.ImmutableRoom;
import com.example.aqoda.resource.booking.entities.BookingEntity;
import com.example.aqoda.resource.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Mono<ImmutableBooking> book(BookingEntity booking) {
        return bookingRepository.save(booking)
                .map(newBooking -> ImmutableBooking.builder()
                        .bookingId(newBooking.getBookingId())
                        .roomNo(newBooking.getRoomNo())
                        .guestId(newBooking.getGuestId())
                        .build()
                );
    }

    @Override
    public Mono<ImmutableBooking> checkout(UUID guestId, Long roomNo) {
        return bookingRepository.findBookingEntityByGuestIdAndAndRoomNo(guestId, roomNo)
                .flatMap(booking -> bookingRepository.delete(booking)
                        .map(ignored -> ImmutableBooking.builder()
                                .bookingId(booking.getBookingId())
                                .roomNo(booking.getRoomNo())
                                .guestId(booking.getGuestId())
                                .build()
                        )
                );
    }

    @Override
    public Mono<ImmutableBooking> findByRoomNo(Long roomNo) {
        return bookingRepository.findByRoomNo(roomNo)
                .map(booking -> ImmutableBooking.builder()
                        .bookingId(booking.getBookingId())
                        .roomNo(booking.getRoomNo())
                        .guestId(booking.getGuestId())
                        .build()
                );
    }

    @Override
    public Flux<ImmutableBooking> findByGuestId(UUID guestId) {
        return bookingRepository.findBookingEntityByGuestId(guestId)
                .map(booking -> ImmutableBooking.builder()
                        .bookingId(booking.getBookingId())
                        .roomNo(booking.getRoomNo())
                        .guestId(booking.getGuestId())
                        .build()
                );
    }

    @Override
    public Flux<ImmutableBooking> findAll() {
        return bookingRepository.findAll()
                .map(booking -> ImmutableBooking.builder()
                        .bookingId(booking.getBookingId())
                        .roomNo(booking.getRoomNo())
                        .guestId(booking.getGuestId())
                        .build()
                );
    }
}
