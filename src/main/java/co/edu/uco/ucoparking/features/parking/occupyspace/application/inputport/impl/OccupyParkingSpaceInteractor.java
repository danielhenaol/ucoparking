package co.edu.uco.ucoparking.features.parking.occupyspace.application.inputport.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.ucoparking.application.inputport.OccupyParkingSpaceInputPort;
import co.edu.uco.ucoparking.domain.model.Reservation;
import co.edu.uco.ucoparking.features.parking.occupyspace.application.usecase.OccupyParkingSpaceUseCase;

/**
 * Interactor que conecta el controlador con el caso de uso de ocupar espacio.
 */
@Service
public class OccupyParkingSpaceInteractor implements OccupyParkingSpaceInputPort {

    private final OccupyParkingSpaceUseCase occupyParkingSpaceUseCase;

    public OccupyParkingSpaceInteractor(OccupyParkingSpaceUseCase occupyParkingSpaceUseCase) {
        this.occupyParkingSpaceUseCase = occupyParkingSpaceUseCase;
    }

    @Override
    public Reservation execute(UUID reservationId) {
        return occupyParkingSpaceUseCase.execute(reservationId);
    }
}