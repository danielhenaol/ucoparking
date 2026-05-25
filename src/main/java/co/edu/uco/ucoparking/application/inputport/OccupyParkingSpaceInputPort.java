package co.edu.uco.ucoparking.application.inputport;

import java.util.UUID;

import co.edu.uco.ucoparking.domain.model.Reservation;

public interface OccupyParkingSpaceInputPort {

    Reservation execute(UUID reservationId);
}