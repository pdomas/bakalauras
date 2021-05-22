package com.example.Bakalaurinis.service;

import com.example.Bakalaurinis.entity.Reservation;
import com.example.Bakalaurinis.entity.ReservationHistory;
import com.example.Bakalaurinis.entity.dto.ReservationHistoryDTO;
import com.example.Bakalaurinis.repository.ReservationHistoryRepository;
import com.example.Bakalaurinis.service.dao.ReservationHistoryService;
import com.example.Bakalaurinis.service.mapper.DtoMapper;
import com.example.Bakalaurinis.service.mapper.EntityMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationHistoryServiceImpl implements ReservationHistoryService {

    private ReservationHistoryRepository reservationHistoryRepository;
    private EntityMapper entityMapper;
    private DtoMapper dtoMapper;

    public ReservationHistoryServiceImpl(ReservationHistoryRepository reservationHistoryRepository, EntityMapper entityMapper, DtoMapper dtoMapper) {
        this.reservationHistoryRepository = reservationHistoryRepository;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<ReservationHistoryDTO> getAllReservationHistories() {
        return reservationHistoryRepository.findAll()
                .stream()
                .map(reservationHistory -> entityMapper.convertReservationHistoryEntityToDTO(reservationHistory))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationHistoryDTO getSingleReservationHistoryById(UUID reservation_history_id) {
        if (reservation_history_id == null) {
            throw new EntityNotFoundException(reservation_history_id.toString());
        }
        return entityMapper.convertReservationHistoryEntityToDTO(reservationHistoryRepository.getOne(reservation_history_id));
    }

    @Override
    public ReservationHistoryDTO createReservationHistory(ReservationHistoryDTO reservationHistoryDTO) {
//        Reservation reservation = dtoMapper.convertReservationDtoToEntity(reservationDTO);
//        Reservation savedReservation = reservationRepository.save(reservation);
        ReservationHistory reservationHistory = dtoMapper.convertReservationHistoryDtoToEntity(reservationHistoryDTO);
        ReservationHistory savedReservationHistory = reservationHistoryRepository.save(reservationHistory);
        return entityMapper.convertReservationHistoryEntityToDTO(savedReservationHistory);
    }

    @Override
    public ReservationHistoryDTO updateReservationHistory(ReservationHistoryDTO reservationHistoryDTO) {
        UUID id = reservationHistoryDTO.getReservation_history_id();
        if (reservationHistoryRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(id.toString());
        }
        ReservationHistory reservationHistory = dtoMapper.convertReservationHistoryDtoToEntity(reservationHistoryDTO);
        ReservationHistory updatedReservation = reservationHistoryRepository.save(reservationHistory);
        return entityMapper.convertReservationHistoryEntityToDTO(updatedReservation);
    }

    @Override
    public void deleteReservationHistory(UUID reservation_id) {
        if (!reservationHistoryRepository.existsById(reservation_id)) {
            throw new EntityNotFoundException(reservation_id.toString());
        }
        reservationHistoryRepository.deleteById(reservation_id);

    }
}