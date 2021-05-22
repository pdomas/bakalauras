package com.example.Bakalaurinis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Entity(name = "reservation_history")
public class ReservationHistory {

    @Id
    @Column(name = "reservation_history_id")
    private UUID reservation_history_id;

    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "location_address")
    private String location_address;

    @Column(name = "contact_info")
    private String contact_info;

    @Column(name = "total_price")
    private double total_price;

    @Column(name = "date")
    private String date;

    public ReservationHistory(UUID reservation_history_id, UUID user_id, String location_address, String contact_info, double total_price, String date) {
        this.reservation_history_id = reservation_history_id;
        this.user_id = user_id;
        this.location_address = location_address;
        this.contact_info = contact_info;
        this.total_price = total_price;
        this.date = date;
    }

    public ReservationHistory() {
    }

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
