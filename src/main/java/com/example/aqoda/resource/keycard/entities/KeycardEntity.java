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
public class KeycardEntity implements Persistable<Long> {

    @Id
    @Column("keycard_no")
    private final Long keycardNo;

    @Column("room_no")
    private Long roomNo;

    @Column("holder")
    private UUID holder;

    @Override
    public Long getId() {
        return keycardNo;
    }

    @Override
    public boolean isNew() {
        return keycardNo == null;
    }
}
