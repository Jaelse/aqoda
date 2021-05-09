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
    keychain_no LONG PRIMARY KEY,
    hotel_id    LONG,
    foreign key (hotel_id) references HOTEL (id)
);

CREATE TABLE GUEST
(
    id          LONG PRIMARY KEY,
    name        VARCHAR(255),
    age         INT,
    room_no     Long,
    keychain_no LONG,
    foreign key (room_no) references ROOM (room_no),
    foreign key (keychain_no) references KEYCHAIN (keychain_no)
);