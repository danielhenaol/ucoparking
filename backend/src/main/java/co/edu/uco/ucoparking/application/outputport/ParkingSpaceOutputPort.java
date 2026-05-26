package co.edu.uco.ucoparking.application.outputport;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import co.edu.uco.ucoparking.domain.model.ParkingSpace;
import co.edu.uco.ucoparking.domain.model.ParkingSpaceStatus;

public interface ParkingSpaceOutputPort {

    ParkingSpace save(ParkingSpace parkingSpace);

    Optional<ParkingSpace> findById(UUID id);

    List<ParkingSpace> findAll();

    List<ParkingSpace> findByStatus(ParkingSpaceStatus status);
}