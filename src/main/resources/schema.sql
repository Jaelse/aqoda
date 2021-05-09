CREATE TABLE HOTEL
(
    id   LONG AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE ROOM
(
    room_no    LONG PRIMARY KEY,
    hotel_id   LONG NOT NULL,
    keycard_no LONG,
    booking_id UUID,
    foreign key (hotel_id) references HOTEL (id)
);

CREATE TABLE KEYCARD
(
    keycard_no LONG AUTO_INCREMENT PRIMARY KEY,
    room_no    LONG,
    holder     UUID,
    foreign key (room_no) references ROOM (room_no)
);

CREATE TABLE GUEST
(
    id   UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name VARCHAR(255),
    age  INT,
    check (age > 1 and age < 150)
);

CREATE TABLE BOOKING
(
    booking_id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    room_no    Long,
    guest_id   UUID,
    foreign key (room_no) references ROOM (room_no),
    foreign key (guest_id) references GUEST (id)
);

ALTER TABLE ROOM
    ADD FOREIGN KEY (keycard_no)
        REFERENCES KEYCARD (keycard_no);

ALTER TABLE ROOM
    ADD FOREIGN KEY (booking_id)
        REFERENCES BOOKING (booking_id);

ALTER TABLE KEYCARD
    ADD FOREIGN KEY (holder)
        REFERENCES GUEST (id);