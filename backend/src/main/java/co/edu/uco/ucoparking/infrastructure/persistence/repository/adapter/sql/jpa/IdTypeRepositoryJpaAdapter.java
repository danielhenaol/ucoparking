package co.edu.uco.ucoparking.infrastructure.persistence.repository.adapter.sql.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import co.edu.uco.ucoparking.domain.model.IdType;
import co.edu.uco.ucoparking.infrastructure.persistence.mapper.IdTypeMapperJPA;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.IdTypeRepositoryPort;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.IdTypeJpaRepository;

@Repository
public class IdTypeRepositoryJpaAdapter implements IdTypeRepositoryPort {
    private final IdTypeJpaRepository repository;
    private final IdTypeMapperJPA mapper;
    public IdTypeRepositoryJpaAdapter(IdTypeJpaRepository repository, IdTypeMapperJPA mapper) { this.repository = repository; this.mapper = mapper; }
    public IdType create(IdType item) { return mapper.toDomainEntity(repository.save(mapper.toJpaEntity(item))); }
    public Optional<IdType> findById(UUID id) { return repository.findById(id).map(mapper::toDomainEntity); }
    public List<IdType> findAll() { return repository.findAll().stream().map(mapper::toDomainEntity).toList(); }
    public IdType update(UUID id, IdType item) { return mapper.toDomainEntity(repository.save(mapper.toJpaEntity(new IdType(id, item.getName())))); }
    public void deleteById(UUID id) { repository.deleteById(id); }
}
