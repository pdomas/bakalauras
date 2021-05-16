package com.example.Bakalaurinis.service.mapper;

import com.example.Bakalaurinis.entity.Location;
import com.example.Bakalaurinis.entity.dto.LocationDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EntityMapper {

    public LocationDTO convertLocationEntityToDTO(Location location, UUID location_id) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocation_id(location.getLocation_id());
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setLongitude(location.getLongitude());
        locationDTO.setLocation_address(location.getLocation_address());
        locationDTO.setContact_info(location.getContact_info());
        locationDTO.setReservation_status(location.isReservation_status());
        return locationDTO;
    }

    public LocationDTO convertLocationEntityToDTO(Location location) {
        return convertLocationEntityToDTO(location, location.getLocation_id());
    }
}
