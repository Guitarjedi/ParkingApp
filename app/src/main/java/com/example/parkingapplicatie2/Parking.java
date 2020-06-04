package com.example.parkingapplicatie2;

import java.io.Serializable;

public class Parking implements Serializable {
    private ParkingStatus parkingStatus;
    private String name;
    private String description;
    private String address;
    private String contactInfo;

    public ParkingStatus getParkingStatus() {
        return parkingStatus;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public Parking(ParkingStatus parkingStatus, String name, String description, String address, String contactInfo) {
        this.parkingStatus = parkingStatus;
        this.name = name;
        this.description = description;
        this.address = address;
        this.contactInfo = contactInfo;
    }
}
