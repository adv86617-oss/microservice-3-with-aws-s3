package com.propertyservice.repository;

import com.propertyservice.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Rooms, Long> {
}
