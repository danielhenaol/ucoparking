package co.edu.uco.ucoparking.infrastructure.persistence.entity;

import java.util.UUID;

/**
 * Entidad de infraestructura para institución.
 */
public class InstituteEntity {

    private UUID id;
    private String name;

    public InstituteEntity(UUID id, String name) {
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
            throw new IllegalArgumentException("El id de la institución es obligatorio.");
        }
        this.id = id;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la institución es obligatorio.");
        }
        this.name = name.trim();
    }
}