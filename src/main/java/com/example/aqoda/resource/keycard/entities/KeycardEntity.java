package com.example.aqoda.resource.keycard.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@Table("keycard")
public class KeycardEntity implements Persistable<UUID> {

    @Id
    @Column("keycard_no")
    private final UUID keycardNo;

    @Column("room_no")
    private Long roomNo;

    @Column("guest_id")
    private UUID guestId;

    @Override
    public UUID getId() {
        return keycardNo;
    }

    @Override
    public boolean isNew() {
        return keycardNo == null;
    }
}
