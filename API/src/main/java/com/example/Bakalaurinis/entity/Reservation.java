package com.example.Bakalaurinis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    private UUID reservation_id;

    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "location_address")
    private String location_address;

    @Column(name = "price_per_hour")
    private double price_per_hour;

    @Column(name = "contact_info")
    private String contact_info;

    @Column(name = "hours_reserved")
    private int hours_reserved;

    @Column(name = "total_price")
    private double total_price;

    public Reservation(UUID reservation_id, UUID user_id, String location_address, double price_per_hour, String contact_info, int hours_reserved, double total_price) {
        this.reservation_id = reservation_id;
        this.user_id = user_id;
        this.location_address = location_address;
        this.price_per_hour = price_per_hour;
        this.contact_info = contact_info;
        this.hours_reserved = hours_reserved;
        this.total_price = total_price;
    }

    public Reservation() {
    }

    public UUID getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(UUID reservation_id) {
        this.reservation_id = reservation_id;
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

    public double getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(double price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public int getHours_reserved() {
        return hours_reserved;
    }

    public void setHours_reserved(int hours_reserved) {
        this.hours_reserved = hours_reserved;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}