package com.example.aqoda.resource.room.repository;

import com.example.aqoda.resource.room.entities.RoomEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends ReactiveCrudRepository<RoomEntity, Long> {
}
