package com.example.aqoda.resource.guest.entities;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Setter
@Getter
@Builder
@Table("guest")
public class GuestEntity {

    @Id
    private UUID id;

    @NonNull
    @Column("name")
    private String name;

    @NonNull
    @Column("age")
    private Integer age;

    @Column("room_no")
    private Long roomNo;

    @Column("keychain_no")
    private UUID keychainNo;

}
