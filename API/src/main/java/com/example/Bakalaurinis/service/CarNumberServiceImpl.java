package com.example.Bakalaurinis.service;

import com.example.Bakalaurinis.entity.CarNumber;
import com.example.Bakalaurinis.entity.dto.CarNumberDTO;
import com.example.Bakalaurinis.repository.CarNumberRepository;
import com.example.Bakalaurinis.service.dao.CarNumberService;
import com.example.Bakalaurinis.service.mapper.DtoMapper;
import com.example.Bakalaurinis.service.mapper.EntityMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarNumberServiceImpl implements CarNumberService {

    private CarNumberRepository carNumberRepository;
    private EntityMapper entityMapper;
    private DtoMapper dtoMapper;

    public CarNumberServiceImpl(CarNumberRepository carNumberRepository, EntityMapper entityMapper, DtoMapper dtoMapper) {
        this.carNumberRepository = carNumberRepository;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<CarNumberDTO> getAllCarNumbers() {
        return carNumberRepository.findAll()
                .stream()
                .map(carNumber -> entityMapper.convertCarNumberEntityToDTO(carNumber))
                .collect(Collectors.toList());
    }

    @Override
    public CarNumberDTO getSingleCarNumberById(UUID car_number_id) {
        if (car_number_id == null) {
            throw new EntityNotFoundException(car_number_id.toString());
        }
        return entityMapper.convertCarNumberEntityToDTO(carNumberRepository.getOne(car_number_id));
    }

    @Override
    public CarNumberDTO createCarNumber(CarNumberDTO carNumberDTO) {
        CarNumber carNumber = dtoMapper.convertCarNumberDtoToEntity(carNumberDTO);
        CarNumber savedCarNumber = carNumberRepository.save(carNumber);
        return entityMapper.convertCarNumberEntityToDTO(savedCarNumber);
    }

    @Override
    public CarNumberDTO updateCarNumber(CarNumberDTO carNumberDTO) {
        UUID id = carNumberDTO.getCar_number_id();
        if (carNumberRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(id.toString());
        }
        CarNumber carNumber = dtoMapper.convertCarNumberDtoToEntity(carNumberDTO);
        CarNumber updatedCarNumber = carNumberRepository.save(carNumber);
        return entityMapper.convertCarNumberEntityToDTO(updatedCarNumber);
    }

    @Override
    public void deleteCarNumber(UUID car_number_id) {
        if (!carNumberRepository.existsById(car_number_id)) {
            throw new EntityNotFoundException(car_number_id.toString());
        }
        carNumberRepository.deleteById(car_number_id);

    }
}
