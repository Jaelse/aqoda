package com.example.aqoda.immutable;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Builder
@Getter
@Accessors(fluent = true)
public class ImmutableKeychain {

    private final UUID keychainNo;
    private final Long roomNo;
}
