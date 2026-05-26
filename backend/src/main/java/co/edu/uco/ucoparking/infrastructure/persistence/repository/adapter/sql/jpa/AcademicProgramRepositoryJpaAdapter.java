package co.edu.uco.ucoparking.infrastructure.persistence.repository.adapter.sql.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import co.edu.uco.ucoparking.domain.model.AcademicProgram;
import co.edu.uco.ucoparking.infrastructure.persistence.mapper.AcademicProgramMapperJPA;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.AcademicProgramRepositoryPort;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.AcademicProgramJpaRepository;

@Repository
public class AcademicProgramRepositoryJpaAdapter implements AcademicProgramRepositoryPort {
    private final AcademicProgramJpaRepository repository;
    private final AcademicProgramMapperJPA mapper;
    public AcademicProgramRepositoryJpaAdapter(AcademicProgramJpaRepository repository, AcademicProgramMapperJPA mapper) { this.repository = repository; this.mapper = mapper; }
    public AcademicProgram create(AcademicProgram item) { return mapper.toDomainEntity(repository.save(mapper.toJpaEntity(item))); }
    public Optional<AcademicProgram> findById(UUID id) { return repository.findById(id).map(mapper::toDomainEntity); }
    public List<AcademicProgram> findAll() { return repository.findAll().stream().map(mapper::toDomainEntity).toList(); }
    public AcademicProgram update(UUID id, AcademicProgram item) { return mapper.toDomainEntity(repository.save(mapper.toJpaEntity(new AcademicProgram(id, item.getName())))); }
    public void deleteById(UUID id) { repository.deleteById(id); }
}
