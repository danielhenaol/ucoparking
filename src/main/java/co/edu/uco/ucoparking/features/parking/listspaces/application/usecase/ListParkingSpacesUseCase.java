package co.edu.uco.ucoparking.features.parking.listspaces.application.usecase;

import java.util.List;

import co.edu.uco.ucoparking.domain.model.ParkingSpace;
import co.edu.uco.ucoparking.domain.model.ParkingSpaceStatus;

public interface ListParkingSpacesUseCase {

    List<ParkingSpace> execute();

    List<ParkingSpace> findByStatus(ParkingSpaceStatus status);
}