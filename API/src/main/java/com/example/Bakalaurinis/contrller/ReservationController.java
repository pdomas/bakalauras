package com.example.Bakalaurinis.contrller;

import com.example.Bakalaurinis.entity.dto.ReservationDTO;
import com.example.Bakalaurinis.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/reservation")
public class ReservationController {

    private final ReservationServiceImpl reservationServiceImpl;

    @Autowired
    public ReservationController(ReservationServiceImpl reservationServiceImpl) {
        this.reservationServiceImpl = reservationServiceImpl;
    }
    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        return reservationServiceImpl.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationDTO getSingleReservationById(@PathVariable UUID id) {
        return reservationServiceImpl.getSingleReservationById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDTO saveLocation(@RequestBody @Valid ReservationDTO reservationDTO) {
        return reservationServiceImpl.createReservation(reservationDTO);
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ReservationDTO updatePost(@RequestBody @Valid ReservationDTO reservationDTO) {
        return reservationServiceImpl.updateReservation(reservationDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable UUID id) {
        reservationServiceImpl.deleteReservation(id);
    }

}
