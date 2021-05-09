package com.example.aqoda.resource.keycard.entities;

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
public class KeycardEntity implements Persistable<UUID> {

    @Id
    @Column("keychain_no")
    private final UUID keychainNo;

    @NonNull
    @Column("room_no")
    private final Long roomNo;

    @Column("guest_id")
    private final UUID guestId;

    @Override
    public UUID getId() {
        return keychainNo;
    }

    @Override
    public boolean isNew() {
        return keychainNo == null;
    }
}
