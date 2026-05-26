package co.edu.uco.ucoparking.features.parking.listspaces.application.usecase.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.uco.ucoparking.application.outputport.ParkingSpaceOutputPort;
import co.edu.uco.ucoparking.domain.model.ParkingSpace;
import co.edu.uco.ucoparking.domain.model.ParkingSpaceStatus;
import co.edu.uco.ucoparking.features.parking.listspaces.application.usecase.ListParkingSpacesUseCase;

@Service
public class ListParkingSpacesUseCaseImpl implements ListParkingSpacesUseCase {

    private final ParkingSpaceOutputPort parkingSpaceOutputPort;

    public ListParkingSpacesUseCaseImpl(ParkingSpaceOutputPort parkingSpaceOutputPort) {
        this.parkingSpaceOutputPort = parkingSpaceOutputPort;
    }

    @Override
    public List<ParkingSpace> execute() {
        return parkingSpaceOutputPort.findAll();
    }

    @Override
    public List<ParkingSpace> findByStatus(ParkingSpaceStatus status) {
        return parkingSpaceOutputPort.findByStatus(status);
    }
}