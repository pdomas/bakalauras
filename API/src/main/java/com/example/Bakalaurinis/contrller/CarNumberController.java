package com.example.Bakalaurinis.contrller;

import com.example.Bakalaurinis.entity.dto.CarNumberDTO;
import com.example.Bakalaurinis.service.CarNumberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/car_number")
public class CarNumberController {

    private final CarNumberServiceImpl carNumberServiceImpl;

    @Autowired
    public CarNumberController(CarNumberServiceImpl carNumberServiceImpl) {
        this.carNumberServiceImpl = carNumberServiceImpl;
    }
    @GetMapping
    public List<CarNumberDTO> getAllCarNumbers() {
        return carNumberServiceImpl.getAllCarNumbers();
    }

    @GetMapping("/{id}")
    public CarNumberDTO getSingleCarNumberById(@PathVariable UUID id) {
        return carNumberServiceImpl.getSingleCarNumberById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarNumberDTO createCarNumber(@RequestBody @Valid CarNumberDTO carNumberDTO) {
        return carNumberServiceImpl.createCarNumber(carNumberDTO);
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CarNumberDTO updateCarNumber(@RequestBody @Valid CarNumberDTO carNumberDTO) {
        return carNumberServiceImpl.updateCarNumber(carNumberDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarNumber(@PathVariable UUID id) {
        carNumberServiceImpl.deleteCarNumber(id);
    }

}