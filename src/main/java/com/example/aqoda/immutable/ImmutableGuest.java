package com.example.aqoda.immutable;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Builder
public class ImmutableGuest {

    private final Long id;
    private final String name;
    private final Integer age;

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Integer age() {
        return age;
    }

}
