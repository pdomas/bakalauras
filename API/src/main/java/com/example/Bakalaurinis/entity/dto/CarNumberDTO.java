package com.example.Bakalaurinis.entity.dto;

import java.util.UUID;

public class CarNumberDTO {

    private UUID car_number_id;

    private String car_number;

    private UUID user_id;

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
