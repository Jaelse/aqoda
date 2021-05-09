package com.example.aqoda.immutable;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;


@Builder
public class ImmutableHotel {

    private final Long id;

    private final String name;

    private final List<ImmutableRoom> rooms;

    public Long id() { return id; }

    public String name() { return name; }

    public List<ImmutableRoom> rooms() {
        return new ArrayList<ImmutableRoom>(rooms);
    }
}
