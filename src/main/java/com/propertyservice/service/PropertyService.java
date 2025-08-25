package com.propertyservice.service;

import com.propertyservice.dto.PropertyDto;
import com.propertyservice.dto.RoomsDto;
import com.propertyservice.entity.*;
import com.propertyservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PropertyPhotosRepository propertyPhotosRepository;
    @Autowired
    private S3Service s3Service;

    public Property addProperty(PropertyDto dto, MultipartFile[] files) {

        // Fetch related entities
        City city = cityRepository.findByName(dto.getCity());
        Area area = areaRepository.findByName(dto.getArea());
        State state = stateRepository.findByName(dto.getState());

        // Create property
        Property property = new Property();
        property.setName(dto.getName());
        property.setNumberOfBathrooms(dto.getNumberOfBathrooms());
        property.setNumberOfBeds(dto.getNumberOfBeds());
        property.setNumberOfRooms(dto.getNumberOfRooms());
        property.setNumberOfGuestAllowed(dto.getNumberOfGuestAllowed());
        property.setCity(city);
        property.setArea(area);
        property.setState(state);

        Property savedProperty = propertyRepository.save(property);

        // Save rooms
        dto.getRooms().forEach(roomsDto -> {
            Rooms rooms = new Rooms();
            rooms.setProperty(savedProperty);
            rooms.setRoomType(roomsDto.getRoomType());
            rooms.setBasePrice(roomsDto.getBasePrice());
            roomRepository.save(rooms);
        });

        // Upload files to S3 and save URLs
        List<String> fileUrls = s3Service.uploadFiles(files);
        fileUrls.forEach(url -> {
            PropertyPhotos photo = new PropertyPhotos();
            photo.setUrl(url);
            photo.setProperty(savedProperty);
            propertyPhotosRepository.save(photo);
        });

        // Update DTO
        dto.setImageUrls(fileUrls);

        return savedProperty; // ✅ only one save needed
    }
}
