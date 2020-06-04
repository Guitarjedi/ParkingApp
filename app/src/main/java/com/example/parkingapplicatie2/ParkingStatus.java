package com.example.parkingapplicatie2;

import java.io.Serializable;

public class ParkingStatus implements Serializable {
    private int totalCapacity;
    private int availableCapacity;

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public ParkingStatus(int totalCapacity, int availableCapacity) {
        this.totalCapacity = totalCapacity;
        this.availableCapacity = availableCapacity;
    }
}
