package com.example.Bakalaurinis.entity.dto;

import java.util.Date;
import java.util.UUID;

public class ReservationHistoryDTO {

    private UUID reservation_history_id;

    private UUID user_id;

    private String location_address;

    private String contact_info;

    private double total_price;

    private String date;

    public UUID getReservation_history_id() {
        return reservation_history_id;
    }

    public void setReservation_history_id(UUID reservation_history_id) {
        this.reservation_history_id = reservation_history_id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public String getLocation_address() {
        return location_address;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
