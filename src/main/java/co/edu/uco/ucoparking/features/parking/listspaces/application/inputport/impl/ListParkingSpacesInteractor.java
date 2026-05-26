package co.edu.uco.ucoparking.features.parking.listspaces.application.inputport.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.uco.ucoparking.application.inputport.ListParkingSpacesInputPort;
import co.edu.uco.ucoparking.domain.model.ParkingSpace;
import co.edu.uco.ucoparking.domain.model.ParkingSpaceStatus;
import co.edu.uco.ucoparking.features.parking.listspaces.application.usecase.ListParkingSpacesUseCase;

@Service
public class ListParkingSpacesInteractor implements ListParkingSpacesInputPort {

    private final ListParkingSpacesUseCase listParkingSpacesUseCase;

    public ListParkingSpacesInteractor(ListParkingSpacesUseCase listParkingSpacesUseCase) {
        this.listParkingSpacesUseCase = listParkingSpacesUseCase;
    }

    @Override
    public List<ParkingSpace> execute() {
        return listParkingSpacesUseCase.execute();
    }

    @Override
    public List<ParkingSpace> findByStatus(ParkingSpaceStatus status) {
        return listParkingSpacesUseCase.findByStatus(status);
    }
}