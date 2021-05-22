package com.example.Bakalaurinis.service.mapper;

import com.example.Bakalaurinis.entity.CarNumber;
import com.example.Bakalaurinis.entity.Location;
import com.example.Bakalaurinis.entity.Reservation;
import com.example.Bakalaurinis.entity.ReservationHistory;
import com.example.Bakalaurinis.entity.dto.CarNumberDTO;
import com.example.Bakalaurinis.entity.dto.LocationDTO;
import com.example.Bakalaurinis.entity.dto.ReservationDTO;
import com.example.Bakalaurinis.entity.dto.ReservationHistoryDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EntityMapper {

    public LocationDTO convertLocationEntityToDTO(Location location, UUID location_id) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocation_id(location.getLocation_id());
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setLongitude(location.getLongitude());
        locationDTO.setLocation_address(location.getLocation_address());
        locationDTO.setPrice_per_hour(location.getPrice_per_hour());
        locationDTO.setContact_info(location.getContact_info());
        locationDTO.setReservation_status(location.isReservation_status());
        return locationDTO;
    }

    public LocationDTO convertLocationEntityToDTO(Location location) {
        return convertLocationEntityToDTO(location, location.getLocation_id());
    }

    public CarNumberDTO convertCarNumberEntityToDTO(CarNumber carNumber, UUID car_number_id) {
        CarNumberDTO carNumberDTO = new CarNumberDTO();
        carNumberDTO.setCar_number_id(carNumber.getCar_number_id());
        carNumberDTO.setCar_number(carNumber.getCar_number());
        carNumberDTO.setUser_id(carNumber.getUser_id());
        return carNumberDTO;
    }

    public CarNumberDTO convertCarNumberEntityToDTO(CarNumber carNumber) {
        return convertCarNumberEntityToDTO(carNumber, carNumber.getCar_number_id());
    }

    public ReservationDTO convertReservationEntityToDTO(Reservation reservation, UUID reservation_id) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservation_id(reservation.getReservation_id());
        reservationDTO.setUser_id(reservation.getUser_id());
        reservationDTO.setLocation_address(reservation.getLocation_address());
        reservationDTO.setPrice_per_hour(reservation.getPrice_per_hour());
        reservationDTO.setContact_info(reservation.getContact_info());
        reservationDTO.setHours_reserved(reservation.getHours_reserved());
        reservationDTO.setTotal_price(reservation.getTotal_price());
        return reservationDTO;
    }

    public ReservationDTO convertReservationEntityToDTO(Reservation reservation) {
        return convertReservationEntityToDTO(reservation, reservation.getReservation_id());
    }

    public ReservationHistoryDTO convertReservationHistoryEntityToDTO(ReservationHistory reservationHistory, UUID reservation_id) {
        ReservationHistoryDTO reservationHistoryDTO = new ReservationHistoryDTO();
        reservationHistoryDTO.setReservation_history_id(reservationHistory.getReservation_history_id());
        reservationHistoryDTO.setUser_id(reservationHistory.getUser_id());
        reservationHistoryDTO.setLocation_address(reservationHistory.getLocation_address());
        reservationHistoryDTO.setContact_info(reservationHistory.getContact_info());
        reservationHistoryDTO.setTotal_price(reservationHistory.getTotal_price());
        reservationHistoryDTO.setDate(reservationHistory.getDate());
        return reservationHistoryDTO;
    }

    public ReservationHistoryDTO convertReservationHistoryEntityToDTO(ReservationHistory reservationHistory) {
        return convertReservationHistoryEntityToDTO(reservationHistory, reservationHistory.getReservation_history_id());
    }


}
