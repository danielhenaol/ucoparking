package co.edu.uco.ucoparking.features.parking.reservespace.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.ucoparking.crosscutting.exception.UcoParkingException;
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
        setId(id);
        setParkingSpaceId(parkingSpaceId);
        setVehiclePlate(vehiclePlate);
        setEntryTime(entryTime);
        setExitTime(exitTime);
        setReservationTime(reservationTime);
        setExpirationTime(expirationTime);
        setStatus(status);
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

    private void setId(UUID id) {
        if (id == null) {
            throw new UcoParkingException("El id de la respuesta de reserva no puede ser nulo.");
        }
        this.id = id;
    }

    private void setParkingSpaceId(UUID parkingSpaceId) {
        if (parkingSpaceId == null) {
            throw new UcoParkingException("El id del espacio en la respuesta de reserva no puede ser nulo.");
        }
        this.parkingSpaceId = parkingSpaceId;
    }

    private void setVehiclePlate(String vehiclePlate) {
        if (vehiclePlate == null || vehiclePlate.trim().isEmpty()) {
            throw new UcoParkingException("La placa de la respuesta de reserva no puede ser nula o vacía.");
        }
        this.vehiclePlate = vehiclePlate.trim().toUpperCase();
    }

    private void setEntryTime(LocalDateTime entryTime) {
        if (entryTime == null) {
            throw new UcoParkingException("La hora de entrada de la respuesta no puede ser nula.");
        }
        this.entryTime = entryTime;
    }

    private void setExitTime(LocalDateTime exitTime) {
        if (exitTime == null) {
            throw new UcoParkingException("La hora de salida de la respuesta no puede ser nula.");
        }
        this.exitTime = exitTime;
    }

    private void setReservationTime(LocalDateTime reservationTime) {
        if (reservationTime == null) {
            throw new UcoParkingException("La hora de reserva de la respuesta no puede ser nula.");
        }
        this.reservationTime = reservationTime;
    }

    private void setExpirationTime(LocalDateTime expirationTime) {
        if (expirationTime == null) {
            throw new UcoParkingException("La hora de expiración de la respuesta no puede ser nula.");
        }
        this.expirationTime = expirationTime;
    }

    private void setStatus(ReservationStatus status) {
        if (status == null) {
            throw new UcoParkingException("El estado de la respuesta de reserva no puede ser nulo.");
        }
        this.status = status;
    }
}