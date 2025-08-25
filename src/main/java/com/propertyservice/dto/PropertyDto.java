package com.propertyservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PropertyDto {
    private long id;
    private String name;
    private int numberOfBeds;
    private int numberOfRooms;
    private int numberOfBathrooms;
    private int numberOfGuestAllowed;
    private String city;
    private String area;
    private String state;

    private List<RoomsDto>rooms;
    private List<String>imageUrls;
}
