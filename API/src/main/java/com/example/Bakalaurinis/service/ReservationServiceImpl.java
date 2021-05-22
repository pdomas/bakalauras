package com.example.Bakalaurinis.service;

import com.example.Bakalaurinis.entity.Reservation;
import com.example.Bakalaurinis.entity.dto.ReservationDTO;
import com.example.Bakalaurinis.repository.ReservationRepository;
import com.example.Bakalaurinis.service.dao.ReservationService;
import com.example.Bakalaurinis.service.mapper.DtoMapper;
import com.example.Bakalaurinis.service.mapper.EntityMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private EntityMapper entityMapper;
    private DtoMapper dtoMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, EntityMapper entityMapper, DtoMapper dtoMapper) {
        this.reservationRepository = reservationRepository;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(reservation -> entityMapper.convertReservationEntityToDTO(reservation))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO getSingleReservationById(UUID reservation_id) {
        if (reservation_id == null) {
            throw new EntityNotFoundException(reservation_id.toString());
        }
        return entityMapper.convertReservationEntityToDTO(reservationRepository.getOne(reservation_id));
    }

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = dtoMapper.convertReservationDtoToEntity(reservationDTO);
        Reservation savedReservation = reservationRepository.save(reservation);
        return entityMapper.convertReservationEntityToDTO(savedReservation);
    }

    @Override
    public ReservationDTO updateReservation(ReservationDTO reservationDTO) {
        UUID id = reservationDTO.getReservation_id();
        if (reservationRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(id.toString());
        }
        Reservation reservation = dtoMapper.convertReservationDtoToEntity(reservationDTO);
        Reservation updatedReservation = reservationRepository.save(reservation);
        return entityMapper.convertReservationEntityToDTO(updatedReservation);
    }

    @Override
    public void deleteReservation(UUID reservation_id) {
        if (!reservationRepository.existsById(reservation_id)) {
            throw new EntityNotFoundException(reservation_id.toString());
        }
        reservationRepository.deleteById(reservation_id);

    }
}
