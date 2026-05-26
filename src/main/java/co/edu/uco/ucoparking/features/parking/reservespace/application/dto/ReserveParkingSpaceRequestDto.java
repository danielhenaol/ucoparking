package co.edu.uco.ucoparking.features.parking.reservespace.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReserveParkingSpaceRequestDto {

    @NotNull(message = "El espacio de parqueadero es obligatorio.")
    private UUID parkingSpaceId;

    @NotBlank(message = "La placa del vehículo es obligatoria.")
    private String vehiclePlate;

    @NotNull(message = "La hora de entrada es obligatoria.")
    private LocalDateTime entryTime;

    @NotNull(message = "La hora de salida es obligatoria.")
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