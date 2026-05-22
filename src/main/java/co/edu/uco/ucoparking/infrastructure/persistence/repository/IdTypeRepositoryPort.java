package co.edu.uco.ucoparking.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import co.edu.uco.ucoparking.domain.model.IdType;

public interface IdTypeRepositoryPort {
    IdType create(IdType idType);
    Optional<IdType> findById(UUID id);
    List<IdType> findAll();
    IdType update(UUID id, IdType idType);
    void deleteById(UUID id);
}
