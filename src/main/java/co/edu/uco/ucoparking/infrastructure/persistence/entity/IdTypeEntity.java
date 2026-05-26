package co.edu.uco.ucoparking.infrastructure.persistence.entity;

import java.util.UUID;

public class IdTypeEntity {

    private UUID id;
    private String name;

    public IdTypeEntity(UUID id, String name) {
        setId(id);
        setName(name);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void setId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El id del tipo de identificación es obligatorio.");
        }
        this.id = id;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del tipo de identificación es obligatorio.");
        }
        this.name = name.trim();
    }
}