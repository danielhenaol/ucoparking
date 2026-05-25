package co.edu.uco.ucoparking.features.parking.occupyspace.application.usecase;

import java.util.UUID;

import co.edu.uco.ucoparking.domain.model.Reservation;

/**
 * Caso de uso para ocupar una reserva de parqueadero.
 */
public interface OccupyParkingSpaceUseCase {

    Reservation execute(UUID reservationId);
}