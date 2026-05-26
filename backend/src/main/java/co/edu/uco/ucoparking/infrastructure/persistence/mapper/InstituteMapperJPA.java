package co.edu.uco.ucoparking.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import co.edu.uco.ucoparking.domain.model.Institute;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity.InstituteJPAEntity;

@Component
public class InstituteMapperJPA implements MapperJPA<Institute, InstituteJPAEntity> {
    @Override
    public InstituteJPAEntity toJpaEntity(Institute domain) {
        return new InstituteJPAEntity(domain.getId(), domain.getName());
    }
    @Override
    public Institute toDomainEntity(InstituteJPAEntity jpaEntity) {
        return new Institute(jpaEntity.getId(), jpaEntity.getName());
    }
}
