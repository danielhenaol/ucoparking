package co.edu.uco.ucoparking.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import co.edu.uco.ucoparking.domain.model.IdType;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity.IdTypeJPAEntity;

@Component
public class IdTypeMapperJPA implements MapperJPA<IdType, IdTypeJPAEntity> {
    @Override
    public IdTypeJPAEntity toJpaEntity(IdType domain) {
        return new IdTypeJPAEntity(domain.getId(), domain.getName());
    }
    @Override
    public IdType toDomainEntity(IdTypeJPAEntity jpaEntity) {
        return new IdType(jpaEntity.getId(), jpaEntity.getName());
    }
}
