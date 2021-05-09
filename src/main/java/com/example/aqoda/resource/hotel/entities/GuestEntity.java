package com.example.aqoda.resource.hotel.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Setter
@Getter
@Builder
public class GuestEntity {

    @Id
    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Integer age;

}
