package com.example.Bakalaurinis.service.dao;

import com.example.Bakalaurinis.entity.dto.CarNumberDTO;
import com.example.Bakalaurinis.entity.dto.LocationDTO;

import java.util.List;
import java.util.UUID;

public interface CarNumberService {
    List<CarNumberDTO> getAllCarNumbers();

    CarNumberDTO getSingleCarNumberById(UUID car_number_id);

    CarNumberDTO createCarNumber(CarNumberDTO car_number_id);

    CarNumberDTO updateCarNumber(CarNumberDTO car_number_id);

    void deleteCarNumber(UUID car_number_id);
}
