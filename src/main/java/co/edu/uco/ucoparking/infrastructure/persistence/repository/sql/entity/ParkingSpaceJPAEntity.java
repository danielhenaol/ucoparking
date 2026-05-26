package co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity;

import java.util.UUID;

import co.edu.uco.ucoparking.domain.model.ParkingSpaceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parking_spaces")
public class ParkingSpaceJPAEntity {

    @Id
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "code", nullable = false, unique = true, length = 20)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private ParkingSpaceStatus status;

    public ParkingSpaceJPAEntity() {
    }

    public ParkingSpaceJPAEntity(UUID id, String code, ParkingSpaceStatus status) {
        this.id = id;
        this.code = code;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public ParkingSpaceStatus getStatus() {
        return status;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStatus(ParkingSpaceStatus status) {
        this.status = status;
    }
}