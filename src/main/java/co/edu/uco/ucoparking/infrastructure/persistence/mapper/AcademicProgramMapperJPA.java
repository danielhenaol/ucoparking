package co.edu.uco.ucoparking.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import co.edu.uco.ucoparking.domain.model.AcademicProgram;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity.AcademicProgramJPAEntity;

@Component
public class AcademicProgramMapperJPA implements MapperJPA<AcademicProgram, AcademicProgramJPAEntity> {
    @Override
    public AcademicProgramJPAEntity toJpaEntity(AcademicProgram domain) {
        return new AcademicProgramJPAEntity(domain.getId(), domain.getName());
    }
    @Override
    public AcademicProgram toDomainEntity(AcademicProgramJPAEntity jpaEntity) {
        return new AcademicProgram(jpaEntity.getId(), jpaEntity.getName());
    }
}
