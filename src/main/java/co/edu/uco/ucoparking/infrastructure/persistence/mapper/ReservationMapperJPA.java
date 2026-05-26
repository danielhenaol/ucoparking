package co.edu.uco.ucoparking.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import co.edu.uco.ucoparking.domain.model.Reservation;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity.ReservationJPAEntity;

@Component
public class ReservationMapperJPA implements MapperJPA<Reservation, ReservationJPAEntity> {

    @Override
    public ReservationJPAEntity toJpaEntity(Reservation domain) {
        return new ReservationJPAEntity(
                domain.getId(),
                domain.getParkingSpaceId(),
                domain.getVehiclePlate(),
                domain.getEntryTime(),
                domain.getExitTime(),
                domain.getReservationTime(),
                domain.getExpirationTime(),
                domain.getStatus()
        );
    }

    @Override
    public Reservation toDomainEntity(ReservationJPAEntity jpaEntity) {
        return new Reservation(
                jpaEntity.getId(),
                jpaEntity.getParkingSpaceId(),
                jpaEntity.getVehiclePlate(),
                jpaEntity.getEntryTime(),
                jpaEntity.getExitTime(),
                jpaEntity.getReservationTime(),
                jpaEntity.getExpirationTime(),
                jpaEntity.getStatus()
        );
    }
}