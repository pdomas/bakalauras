package com.example.bakalaurodarbas.Entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.UUID;

public class Location implements Serializable {

    @SerializedName("location_id")
    private UUID location_id;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("location_address")
    private String location_address;

    @SerializedName("contact_info")
    private String contact_info;

    @SerializedName("reservation_status")
    private boolean reservation_status;

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
