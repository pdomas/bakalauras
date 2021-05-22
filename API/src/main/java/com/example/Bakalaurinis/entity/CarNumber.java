package com.example.Bakalaurinis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity(name = "car_number")
public class CarNumber {
    @Id
    @Column(name = "car_number_id")
    private UUID car_number_id;

    @Column(name = "car_number")
    private String car_number;

    @Column(name = "user_id")
    private UUID user_id;

    public CarNumber(UUID car_number_id, String car_number, UUID user_id) {
        this.car_number_id = car_number_id;
        this.car_number = car_number;
        this.user_id = user_id;
    }

    public CarNumber() {
    }

    public UUID getCar_number_id() {
        return car_number_id;
    }

    public void setCar_number_id(UUID car_number_id) {
        this.car_number_id = car_number_id;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }
}
