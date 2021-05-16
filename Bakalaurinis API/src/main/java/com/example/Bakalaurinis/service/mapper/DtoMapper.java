package com.example.Bakalaurinis.service.mapper;

import com.example.Bakalaurinis.entity.Location;
import com.example.Bakalaurinis.entity.dto.LocationDTO;
import com.example.Bakalaurinis.repository.LocationRepository;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    private LocationRepository locationRepository;

    public DtoMapper(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location convertLocationDtoToEntity(LocationDTO locationDTO) {
        Location location = new Location();
        location.setLocation_id(locationDTO.getLocation_id());
        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());
        location.setLocation_address(locationDTO.getLocation_address());
        location.setContact_info(locationDTO.getContact_info());
        location.setReservation_status(locationDTO.isReservation_status());
        return location;
    }
}
