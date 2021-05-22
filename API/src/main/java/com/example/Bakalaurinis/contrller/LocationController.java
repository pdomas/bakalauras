package com.example.Bakalaurinis.contrller;


import com.example.Bakalaurinis.entity.dto.LocationDTO;
import com.example.Bakalaurinis.service.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/location")
public class LocationController {

    private final LocationServiceImpl locationServiceImpl;

    @Autowired
    public LocationController(LocationServiceImpl locationServiceImpl) {
        this.locationServiceImpl = locationServiceImpl;
    }
    @GetMapping
    public List<LocationDTO> getAllLocations() {
        return locationServiceImpl.getAllLocations();
    }

    @GetMapping("/{id}")
    public LocationDTO getSingleQuestionById(@PathVariable UUID id) {
        return locationServiceImpl.getSingleLocationById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDTO saveLocation(@RequestBody @Valid LocationDTO locationDTO) {
        return locationServiceImpl.createLocation(locationDTO);
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public LocationDTO updatePost(@RequestBody @Valid LocationDTO locationDTO) {
        return locationServiceImpl.updateLocation(locationDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable UUID id) {
        locationServiceImpl.deleteLocation(id);
    }

}
