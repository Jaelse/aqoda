CREATE TABLE HOTEL
(
    id   LONG AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE ROOM
(
    room_no  LONG PRIMARY KEY,
    hotel_id LONG NOT NULL,
    foreign key (hotel_id) references HOTEL (id)
);

CREATE TABLE KEYCHAIN
(
    keychain_no UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    room_no     LONG,
    guest_id    UUID,
    foreign key (room_no) references ROOM (room_no)
);

CREATE TABLE GUEST
(
    id          UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name        VARCHAR(255),
    age         INT,
    room_no     Long,
    keychain_no UUID,
    check (age > 1 and age < 150),
    foreign key (room_no) references ROOM (room_no),
    foreign key (keychain_no) references KEYCHAIN (keychain_no)
);

ALTER TABLE KEYCHAIN
    ADD FOREIGN KEY (guest_id)
        REFERENCES GUEST (id);