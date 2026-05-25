package co.edu.uco.ucoparking.features.parking.reservespace.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReserveParkingSpaceRequestDto {

    private UUID parkingSpaceId;
    private String vehiclePlate;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public ReserveParkingSpaceRequestDto() {
    }

    public UUID getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(UUID parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
}