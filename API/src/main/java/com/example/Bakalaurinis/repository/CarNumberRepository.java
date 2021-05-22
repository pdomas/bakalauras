package com.example.Bakalaurinis.repository;

import com.example.Bakalaurinis.entity.CarNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarNumberRepository extends JpaRepository<CarNumber, UUID> {
}
