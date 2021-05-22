package com.example.Bakalaurinis.repository;

import com.example.Bakalaurinis.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
}
