package com.example.Bakalaurinis.service.dao;

import com.example.Bakalaurinis.entity.dto.LocationDTO;
import com.example.Bakalaurinis.entity.dto.ReservationHistoryDTO;

import java.util.List;
import java.util.UUID;

public interface ReservationHistoryService {
    List<ReservationHistoryDTO> getAllReservationHistories();

    ReservationHistoryDTO getSingleReservationHistoryById(UUID reservation_history_id);

    ReservationHistoryDTO createReservationHistory(ReservationHistoryDTO reservationHistoryDTO);

    ReservationHistoryDTO updateReservationHistory(ReservationHistoryDTO reservationHistoryDTO);

    void deleteReservationHistory(UUID reservation_history_id);
}
