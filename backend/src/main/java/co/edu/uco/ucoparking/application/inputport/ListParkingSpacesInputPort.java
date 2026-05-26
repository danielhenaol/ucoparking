package co.edu.uco.ucoparking.application.inputport;

import java.util.List;

import co.edu.uco.ucoparking.domain.model.ParkingSpace;
import co.edu.uco.ucoparking.domain.model.ParkingSpaceStatus;

public interface ListParkingSpacesInputPort {

    List<ParkingSpace> execute();

    List<ParkingSpace> findByStatus(ParkingSpaceStatus status);
}