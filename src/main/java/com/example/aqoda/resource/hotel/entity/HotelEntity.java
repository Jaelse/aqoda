package com.example.aqoda.resource.hotel.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@Builder
public class HotelEntity {

    @Id
    private Long id;

    private  String name;

    private Integer noOfFloors;

    private List<RoomEntity> rooms;

}
