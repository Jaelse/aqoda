package com.example.aqoda.resource.hotel.entities;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@Table("hotel")
public class HotelEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private  String name;

}
