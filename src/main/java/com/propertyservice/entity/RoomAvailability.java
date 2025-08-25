package com.propertyservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "room_availability")
public class RoomAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate availableDate;
    private int availableCount;
    private double price;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Rooms room;

}
