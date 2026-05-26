package co.edu.uco.ucoparking.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import co.edu.uco.ucoparking.domain.model.ParkingSpace;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity.ParkingSpaceJPAEntity;

@Component
public class ParkingSpaceMapperJPA implements MapperJPA<ParkingSpace, ParkingSpaceJPAEntity> {

    @Override
    public ParkingSpaceJPAEntity toJpaEntity(ParkingSpace domain) {
        return new ParkingSpaceJPAEntity(
                domain.getId(),
                domain.getCode(),
                domain.getStatus()
        );
    }

    @Override
    public ParkingSpace toDomainEntity(ParkingSpaceJPAEntity jpaEntity) {
        return new ParkingSpace(
                jpaEntity.getId(),
                jpaEntity.getCode(),
                jpaEntity.getStatus()
        );
    }
}