package co.edu.uco.ucoparking.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import co.edu.uco.ucoparking.crosscutting.exception.UcoParkingException;
import co.edu.uco.ucoparking.domain.model.ParkingSpace;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity.ParkingSpaceJPAEntity;

@Component
public class ParkingSpaceMapperJPA implements MapperJPA<ParkingSpace, ParkingSpaceJPAEntity> {

    @Override
    public ParkingSpaceJPAEntity toJpaEntity(ParkingSpace domain) {
        if (domain == null) {
            throw new UcoParkingException("El espacio de parqueadero de dominio no puede ser nulo.");
        }

        return new ParkingSpaceJPAEntity(
                domain.getId(),
                domain.getCode(),
                domain.getStatus()
        );
    }

    @Override
    public ParkingSpace toDomainEntity(ParkingSpaceJPAEntity jpaEntity) {
        if (jpaEntity == null) {
            throw new UcoParkingException("La entidad JPA del espacio de parqueadero no puede ser nula.");
        }

        return new ParkingSpace(
                jpaEntity.getId(),
                jpaEntity.getCode(),
                jpaEntity.getStatus()
        );
    }
}