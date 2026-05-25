package co.edu.uco.ucoparking.features.parking.reservespace.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.ucoparking.domain.model.ReservationStatus;

public class ReservationResponseDto {

    private UUID id;
    private UUID parkingSpaceId;
    private String vehiclePlate;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private LocalDateTime reservationTime;
    private LocalDateTime expirationTime;
    private ReservationStatus status;

    public ReservationResponseDto(UUID id, UUID parkingSpaceId, String vehiclePlate,
                                  LocalDateTime entryTime, LocalDateTime exitTime,
                                  LocalDateTime reservationTime, LocalDateTime expirationTime,
                                  ReservationStatus status) {
        this.id = id;
        this.parkingSpaceId = parkingSpaceId;
        this.vehiclePlate = vehiclePlate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.reservationTime = reservationTime;
        this.expirationTime = expirationTime;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public UUID getParkingSpaceId() {
        return parkingSpaceId;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}