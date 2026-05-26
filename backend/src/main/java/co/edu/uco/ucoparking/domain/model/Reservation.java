package co.edu.uco.ucoparking.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {

    private UUID id;
    private UUID parkingSpaceId;
    private String vehiclePlate;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private LocalDateTime reservationTime;
    private LocalDateTime expirationTime;
    private ReservationStatus status;

    public Reservation(UUID id, UUID parkingSpaceId, String vehiclePlate, LocalDateTime entryTime,
                       LocalDateTime exitTime, LocalDateTime reservationTime,
                       LocalDateTime expirationTime, ReservationStatus status) {
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

    public Reservation occupy() {
        return new Reservation(
                id,
                parkingSpaceId,
                vehiclePlate,
                entryTime,
                exitTime,
                reservationTime,
                expirationTime,
                ReservationStatus.OCCUPIED
        );
    }

    public Reservation expire() {
        return new Reservation(
                id, parkingSpaceId, vehiclePlate,
                entryTime, exitTime, reservationTime,
                expirationTime, ReservationStatus.EXPIRED
        );
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationTime);
    }

    private void setId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El id de la reserva es obligatorio.");
        }

        this.id = id;
    }

    private void setParkingSpaceId(UUID parkingSpaceId) {
        if (parkingSpaceId == null) {
            throw new IllegalArgumentException("El id del espacio de parqueadero es obligatorio.");
        }

        this.parkingSpaceId = parkingSpaceId;
    }

    private void setVehiclePlate(String vehiclePlate) {
        if (vehiclePlate == null || vehiclePlate.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa del vehículo es obligatoria.");
        }

        this.vehiclePlate = vehiclePlate.trim().toUpperCase();
    }

    private void setEntryTime(LocalDateTime entryTime) {
        if (entryTime == null) {
            throw new IllegalArgumentException("La hora de entrada es obligatoria.");
        }

        this.entryTime = entryTime;
    }

    private void setExitTime(LocalDateTime exitTime) {
        if (exitTime == null) {
            throw new IllegalArgumentException("La hora de salida es obligatoria.");
        }

        this.exitTime = exitTime;
    }

    private void setReservationTime(LocalDateTime reservationTime) {
        if (reservationTime == null) {
            throw new IllegalArgumentException("La hora de reserva es obligatoria.");
        }

        this.reservationTime = reservationTime;
    }

    private void setExpirationTime(LocalDateTime expirationTime) {
        if (expirationTime == null) {
            throw new IllegalArgumentException("La hora de expiración es obligatoria.");
        }

        this.expirationTime = expirationTime;
    }

    private void setStatus(ReservationStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("El estado de la reserva es obligatorio.");
        }

        this.status = status;
    }
}