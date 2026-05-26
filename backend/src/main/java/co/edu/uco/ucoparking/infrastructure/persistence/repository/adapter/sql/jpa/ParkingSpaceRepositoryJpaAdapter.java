package co.edu.uco.ucoparking.infrastructure.persistence.repository.adapter.sql.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import co.edu.uco.ucoparking.application.outputport.ParkingSpaceOutputPort;
import co.edu.uco.ucoparking.domain.model.ParkingSpace;
import co.edu.uco.ucoparking.domain.model.ParkingSpaceStatus;
import co.edu.uco.ucoparking.infrastructure.persistence.mapper.ParkingSpaceMapperJPA;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.ParkingSpaceJpaRepository;

@Repository
public class ParkingSpaceRepositoryJpaAdapter implements ParkingSpaceOutputPort {

    private final ParkingSpaceJpaRepository parkingSpaceJpaRepository;
    private final ParkingSpaceMapperJPA parkingSpaceMapperJPA;

    public ParkingSpaceRepositoryJpaAdapter(ParkingSpaceJpaRepository parkingSpaceJpaRepository,
                                            ParkingSpaceMapperJPA parkingSpaceMapperJPA) {
        this.parkingSpaceJpaRepository = parkingSpaceJpaRepository;
        this.parkingSpaceMapperJPA = parkingSpaceMapperJPA;
    }

    @Override
    public ParkingSpace save(ParkingSpace parkingSpace) {
        var saved = parkingSpaceJpaRepository.save(parkingSpaceMapperJPA.toJpaEntity(parkingSpace));
        return parkingSpaceMapperJPA.toDomainEntity(saved);
    }

    @Override
    public Optional<ParkingSpace> findById(UUID id) {
        return parkingSpaceJpaRepository.findById(id)
                .map(parkingSpaceMapperJPA::toDomainEntity);
    }

    @Override
    public List<ParkingSpace> findAll() {
        return parkingSpaceJpaRepository.findAll()
                .stream()
                .map(parkingSpaceMapperJPA::toDomainEntity)
                .toList();
    }

    @Override
    public List<ParkingSpace> findByStatus(ParkingSpaceStatus status) {
        return parkingSpaceJpaRepository.findByStatus(status)
                .stream()
                .map(parkingSpaceMapperJPA::toDomainEntity)
                .toList();
    }
}