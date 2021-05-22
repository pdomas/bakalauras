package com.example.Bakalaurinis.service;

import com.example.Bakalaurinis.entity.Location;
import com.example.Bakalaurinis.entity.dto.LocationDTO;
import com.example.Bakalaurinis.repository.LocationRepository;
import com.example.Bakalaurinis.service.dao.LocationService;
import com.example.Bakalaurinis.service.mapper.DtoMapper;
import com.example.Bakalaurinis.service.mapper.EntityMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;
    private EntityMapper entityMapper;
    private DtoMapper dtoMapper;

    public LocationServiceImpl(LocationRepository locationRepository, EntityMapper entityMapper, DtoMapper dtoMapper) {
        this.locationRepository = locationRepository;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll()
                .stream()
                .map(location -> entityMapper.convertLocationEntityToDTO(location))
                .collect(Collectors.toList());
    }

    @Override
    public LocationDTO getSingleLocationById(UUID location_id) {
        if (location_id == null) {
            throw new EntityNotFoundException(location_id.toString());
        }
        return entityMapper.convertLocationEntityToDTO(locationRepository.getOne(location_id));
    }

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        Location location = dtoMapper.convertLocationDtoToEntity(locationDTO);
        Location savedLocation = locationRepository.save(location);
        return entityMapper.convertLocationEntityToDTO(savedLocation);
    }

    @Override
    public LocationDTO updateLocation(LocationDTO locationDTO) {
        UUID id = locationDTO.getLocation_id();
        if (locationRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(id.toString());
        }
        Location location = dtoMapper.convertLocationDtoToEntity(locationDTO);
        Location updatedLocation = locationRepository.save(location);
        return entityMapper.convertLocationEntityToDTO(updatedLocation);
    }

    @Override
    public void deleteLocation(UUID location_id) {
        if (!locationRepository.existsById(location_id)) {
            throw new EntityNotFoundException(location_id.toString());
        }
        locationRepository.deleteById(location_id);

    }
}
