package com.example.Bakalaurinis.contrller;

import com.example.Bakalaurinis.entity.dto.ReservationHistoryDTO;
import com.example.Bakalaurinis.service.ReservationHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/reservation_history")
public class ReservationHistoryController {

    private final ReservationHistoryServiceImpl reservationHistoryServiceImpl;

    @Autowired
    public ReservationHistoryController(ReservationHistoryServiceImpl reservationHistoryServiceImpl) {
        this.reservationHistoryServiceImpl = reservationHistoryServiceImpl;
    }
    @GetMapping
    public List<ReservationHistoryDTO> getAllReservationHistories() {
        return reservationHistoryServiceImpl.getAllReservationHistories();
    }

    @GetMapping("/{id}")
    public ReservationHistoryDTO getSingleReservationHistoryById(@PathVariable UUID id) {
        return reservationHistoryServiceImpl.getSingleReservationHistoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationHistoryDTO createReservationHistory(@RequestBody @Valid ReservationHistoryDTO reservationHistoryDTO) {
        return reservationHistoryServiceImpl.createReservationHistory(reservationHistoryDTO);
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ReservationHistoryDTO updateReservationHistory(@RequestBody @Valid ReservationHistoryDTO reservationHistoryDTO) {
        return reservationHistoryServiceImpl.updateReservationHistory(reservationHistoryDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservationHistory(@PathVariable UUID id) {
        reservationHistoryServiceImpl.deleteReservationHistory(id);
    }

}
