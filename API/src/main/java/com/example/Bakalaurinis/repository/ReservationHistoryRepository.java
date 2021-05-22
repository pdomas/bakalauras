package com.example.Bakalaurinis.repository;

import com.example.Bakalaurinis.entity.ReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationHistoryRepository extends JpaRepository<ReservationHistory, UUID> {
}
