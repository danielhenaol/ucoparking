package co.edu.uco.ucoparking.infrastructure.persistence.repository.adapter.sql.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import co.edu.uco.ucoparking.domain.model.Institute;
import co.edu.uco.ucoparking.infrastructure.persistence.mapper.InstituteMapperJPA;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.InstituteRepositoryPort;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.InstituteJpaRepository;

@Repository
public class InstituteRepositoryJpaAdapter implements InstituteRepositoryPort {
    private final InstituteJpaRepository repository;
    private final InstituteMapperJPA mapper;
    public InstituteRepositoryJpaAdapter(InstituteJpaRepository repository, InstituteMapperJPA mapper) { this.repository = repository; this.mapper = mapper; }
    public Institute create(Institute item) { return mapper.toDomainEntity(repository.save(mapper.toJpaEntity(item))); }
    public Optional<Institute> findById(UUID id) { return repository.findById(id).map(mapper::toDomainEntity); }
    public List<Institute> findAll() { return repository.findAll().stream().map(mapper::toDomainEntity).toList(); }
    public Institute update(UUID id, Institute item) { return mapper.toDomainEntity(repository.save(mapper.toJpaEntity(new Institute(id, item.getName())))); }
    public void deleteById(UUID id) { repository.deleteById(id); }
}
