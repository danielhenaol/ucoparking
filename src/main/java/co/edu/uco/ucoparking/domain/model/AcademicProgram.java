package co.edu.uco.ucoparking.domain.model;

import java.util.UUID;

/**
 * Modelo de dominio que representa un programa académico.
 */
public class AcademicProgram {

    private UUID id;
    private String name;

    public AcademicProgram(UUID id, String name) {
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
            throw new IllegalArgumentException("El id del programa académico es obligatorio.");
        }
        this.id = id;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del programa académico es obligatorio.");
        }
        this.name = name.trim();
    }
}