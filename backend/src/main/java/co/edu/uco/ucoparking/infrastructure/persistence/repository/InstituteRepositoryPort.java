package co.edu.uco.ucoparking.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import co.edu.uco.ucoparking.domain.model.Institute;

public interface InstituteRepositoryPort {
    Institute create(Institute institute);
    Optional<Institute> findById(UUID id);
    List<Institute> findAll();
    Institute update(UUID id, Institute institute);
    void deleteById(UUID id);
}
