package com.example.Bakalaurinis.service.dao;

import com.example.Bakalaurinis.entity.dto.LocationDTO;
import java.util.List;
import java.util.UUID;

public interface LocationService {
    List<LocationDTO> getAllLocations();

    LocationDTO getSingleLocationById(UUID location_id);

    LocationDTO createLocation(LocationDTO locationDTO);

    LocationDTO updateLocation(LocationDTO locationDTO);

    void deleteLocation(UUID location_id);
}
