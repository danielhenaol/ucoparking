package co.edu.uco.ucoparking.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import co.edu.uco.ucoparking.domain.model.AcademicProgram;

public interface AcademicProgramRepositoryPort {
    AcademicProgram create(AcademicProgram academicProgram);
    Optional<AcademicProgram> findById(UUID id);
    List<AcademicProgram> findAll();
    AcademicProgram update(UUID id, AcademicProgram academicProgram);
    void deleteById(UUID id);

    interface ParkingSpaceRepositoryPort {
    }
}
