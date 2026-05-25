package co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.ucoparking.domain.model.ReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad JPA que representa la tabla reservations.
 */
@Entity
@Table(name = "reservations")
public class ReservationJPAEntity {

    @Id
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "parking_space_id", nullable = false, columnDefinition = "uniqueidentifier")
    private UUID parkingSpaceId;

    @Column(name = "vehicle_plate", nullable = false, length = 20)
    private String vehiclePlate;

    @Column(name = "entry_time", nullable = false)
    private LocalDateTime entryTime;

    @Column(name = "exit_time", nullable = false)
    private LocalDateTime exitTime;

    @Column(name = "reservation_time", nullable = false)
    private LocalDateTime reservationTime;

    @Column(name = "expiration_time", nullable = false)
    private LocalDateTime expirationTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private ReservationStatus status;

    public ReservationJPAEntity() {
    }

    public ReservationJPAEntity(UUID id, UUID parkingSpaceId, String vehiclePlate,
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setParkingSpaceId(UUID parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}