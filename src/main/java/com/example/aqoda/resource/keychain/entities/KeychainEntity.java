package com.example.aqoda.resource.keychain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Setter
@Getter
@Builder
@Table("keychain")
public class KeychainEntity implements Persistable<UUID> {

    @Id
    @Column("keychain_no")
    private final UUID keychainNo;

    @NonNull
    @Column("room_no")
    private final Long roomNo;

    @Override
    public UUID getId() {
        return keychainNo;
    }

    @Override
    public boolean isNew() {
        return keychainNo == null;
    }
}
