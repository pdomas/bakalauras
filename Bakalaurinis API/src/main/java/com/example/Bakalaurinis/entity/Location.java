package com.example.Bakalaurinis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity(name = "location")
public class Location {

    @Id
    @Column(name = "location_id")
    private UUID location_id;

    @Column(name = "latitude")
    @NotBlank
    private String latitude;

    @Column(name = "longitude")
    @NotBlank
    private String longitude;

    @Column(name = "location_address")
    @NotBlank
    private String location_address;

    @Column(name = "contact_info")
    @NotBlank
    private String contact_info;

    @Column(name = "reservation_status")
    private boolean reservation_status;

    public Location(UUID location_id, @NotBlank String latitude, @NotBlank String longitude, @NotBlank String location_address, @NotBlank String contact_info, @NotBlank boolean reservation_status) {
        this.location_id = location_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location_address = location_address;
        this.contact_info = contact_info;
        this.reservation_status = reservation_status;
    }

    public Location(){

    }

    @JsonIgnore
    public UUID getLocation_id() {
        return location_id;
    }

    public void setLocation_id(UUID location_id) {
        this.location_id = location_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public boolean isReservation_status() {
        return reservation_status;
    }

    public void setReservation_status(boolean reservation_status) {
        this.reservation_status = reservation_status;
    }
}
