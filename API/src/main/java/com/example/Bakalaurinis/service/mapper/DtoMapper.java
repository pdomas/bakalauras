package com.example.Bakalaurinis.service.mapper;

import com.example.Bakalaurinis.entity.CarNumber;
import com.example.Bakalaurinis.entity.Location;
import com.example.Bakalaurinis.entity.Reservation;
import com.example.Bakalaurinis.entity.ReservationHistory;
import com.example.Bakalaurinis.entity.dto.CarNumberDTO;
import com.example.Bakalaurinis.entity.dto.LocationDTO;
import com.example.Bakalaurinis.entity.dto.ReservationDTO;
import com.example.Bakalaurinis.entity.dto.ReservationHistoryDTO;
import com.example.Bakalaurinis.repository.CarNumberRepository;
import com.example.Bakalaurinis.repository.LocationRepository;
import com.example.Bakalaurinis.repository.ReservationHistoryRepository;
import com.example.Bakalaurinis.repository.ReservationRepository;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DtoMapper {

    private LocationRepository locationRepository;
    private CarNumberRepository carNumberRepository;
    private ReservationHistoryRepository reservationHistoryRepository;
    private ReservationRepository reservationRepository;

    public DtoMapper(LocationRepository locationRepository, CarNumberRepository carNumberRepository, ReservationHistoryRepository reservationHistoryRepository, ReservationRepository reservationRepository) {
        this.locationRepository = locationRepository;
        this.carNumberRepository = carNumberRepository;
        this.reservationHistoryRepository = reservationHistoryRepository;
        this.reservationRepository = reservationRepository;
    }

    public Location convertLocationDtoToEntity(LocationDTO locationDTO) {
        Location location = new Location();
        location.setLocation_id(locationDTO.getLocation_id());
        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());
        location.setLocation_address(locationDTO.getLocation_address());
        location.setPrice_per_hour(locationDTO.getPrice_per_hour());
        location.setContact_info(locationDTO.getContact_info());
        location.setReservation_status(locationDTO.isReservation_status());
        return location;
    }

    public CarNumber convertCarNumberDtoToEntity(CarNumberDTO carNumberDTO) {
        CarNumber carNumber = new CarNumber();
        carNumber.setCar_number_id(carNumberDTO.getCar_number_id());
        carNumber.setCar_number(carNumberDTO.getCar_number());
        carNumber.setUser_id(carNumberDTO.getUser_id());
        return carNumber;
    }

    public Reservation convertReservationDtoToEntity(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setReservation_id(reservationDTO.getReservation_id());
        reservation.setUser_id(reservationDTO.getUser_id());
        reservation.setLocation_address(reservationDTO.getLocation_address());
        reservation.setPrice_per_hour(reservationDTO.getPrice_per_hour());
        reservation.setContact_info(reservationDTO.getContact_info());
        reservation.setHours_reserved(reservationDTO.getHours_reserved());
        reservation.setTotal_price(reservationDTO.getTotal_price());
        return reservation;
    }

    public ReservationHistory convertReservationHistoryDtoToEntity(ReservationHistoryDTO reservationHistoryDTO) {
        ReservationHistory reservationHistory = new ReservationHistory();
        reservationHistory.setReservation_history_id(reservationHistoryDTO.getReservation_history_id());
        reservationHistory.setUser_id(reservationHistoryDTO.getUser_id());
        reservationHistory.setLocation_address(reservationHistoryDTO.getLocation_address());
        reservationHistory.setContact_info(reservationHistoryDTO.getContact_info());
        reservationHistory.setTotal_price(reservationHistoryDTO.getTotal_price());
        reservationHistory.setDate(reservationHistoryDTO.getDate());
        return reservationHistory;
    }
}
