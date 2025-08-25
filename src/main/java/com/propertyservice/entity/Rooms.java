package com.propertyservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roomType;

    @Column(name = "base_price")
    private double basePrice;

    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonBackReference
    private Property property;



}