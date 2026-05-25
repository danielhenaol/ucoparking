package co.edu.uco.ucoparking.domain.model;

import java.util.UUID;

public class ParkingSpace {

    private UUID id;
    private String code;
    private ParkingSpaceStatus status;

    public ParkingSpace(UUID id, String code, ParkingSpaceStatus status) {
        setId(id);
        setCode(code);
        setStatus(status);
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

    public ParkingSpace reserve() {
        if (status != ParkingSpaceStatus.AVAILABLE) {
            throw new IllegalStateException("El espacio no está disponible para reservar.");
        }

        return new ParkingSpace(id, code, ParkingSpaceStatus.RESERVED);
    }

    public ParkingSpace occupy() {
        if (status != ParkingSpaceStatus.RESERVED && status != ParkingSpaceStatus.AVAILABLE) {
            throw new IllegalStateException("El espacio no se puede ocupar.");
        }

        return new ParkingSpace(id, code, ParkingSpaceStatus.OCCUPIED);
    }

    public ParkingSpace release() {
        return new ParkingSpace(id, code, ParkingSpaceStatus.AVAILABLE);
    }

    private void setId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El id del espacio es obligatorio.");
        }

        this.id = id;
    }

    private void setCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del espacio es obligatorio.");
        }

        this.code = code.trim().toUpperCase();
    }

    private void setStatus(ParkingSpaceStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("El estado del espacio es obligatorio.");
        }

        this.status = status;
    }
}