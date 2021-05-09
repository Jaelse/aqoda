package com.example.aqoda.immutable;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Builder
@Getter
@Accessors(fluent = true)
public class ImmutableGuest {

    private final UUID id;
    private final String name;
    private final Integer age;
}
