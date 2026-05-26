package co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "academic_programs")
public class AcademicProgramJPAEntity {
    @Id
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    public AcademicProgramJPAEntity() {}
    public AcademicProgramJPAEntity(UUID id, String name) { this.id = id; this.name = name; }
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
