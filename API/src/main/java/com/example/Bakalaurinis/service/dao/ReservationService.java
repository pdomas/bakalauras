package com.example.Bakalaurinis.service.dao;

import com.example.Bakalaurinis.entity.dto.LocationDTO;
import com.example.Bakalaurinis.entity.dto.ReservationDTO;

import java.util.List;
import java.util.UUID;

public interface ReservationService {
    List<ReservationDTO> getAllReservations();

    ReservationDTO getSingleReservationById(UUID reservation_id);

    ReservationDTO createReservation(ReservationDTO reservationDTO);

    ReservationDTO updateReservation(ReservationDTO reservationDTO);

    void deleteReservation(UUID reservation_id);
}
