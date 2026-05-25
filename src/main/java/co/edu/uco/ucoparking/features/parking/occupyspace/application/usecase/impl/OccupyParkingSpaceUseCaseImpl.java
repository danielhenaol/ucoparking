package co.edu.uco.ucoparking.features.parking.occupyspace.application.usecase.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uco.ucoparking.application.outputport.ParkingSpaceOutputPort;
import co.edu.uco.ucoparking.application.outputport.ReservationOutputPort;
import co.edu.uco.ucoparking.crosscutting.exception.UcoParkingException;
import co.edu.uco.ucoparking.domain.model.ReservationStatus;
import co.edu.uco.ucoparking.features.parking.occupyspace.application.usecase.OccupyParkingSpaceUseCase;

/**
 * Implementación del caso de uso para ocupar un espacio reservado.
 */
@Service
public class OccupyParkingSpaceUseCaseImpl implements OccupyParkingSpaceUseCase {

    private final ReservationOutputPort reservationOutputPort;
    private final ParkingSpaceOutputPort parkingSpaceOutputPort;

    public OccupyParkingSpaceUseCaseImpl(ReservationOutputPort reservationOutputPort,
                                         ParkingSpaceOutputPort parkingSpaceOutputPort) {
        this.reservationOutputPort = reservationOutputPort;
        this.parkingSpaceOutputPort = parkingSpaceOutputPort;
    }

    @Override
    @Transactional
    public co.edu.uco.ucoparking.domain.model.Reservation execute(UUID reservationId) {
        if (reservationId == null) {
            throw new UcoParkingException("El id de la reserva es obligatorio.");
        }

        var reservation = reservationOutputPort.findById(reservationId)
                .orElseThrow(() -> new UcoParkingException("No se encontró la reserva."));

        if (reservation.getStatus() != ReservationStatus.ACTIVE) {
            throw new UcoParkingException("La reserva no está activa.");
        }

        if (reservation.isExpired()) {
            throw new UcoParkingException("La reserva ya expiró.");
        }

        var parkingSpace = parkingSpaceOutputPort.findById(reservation.getParkingSpaceId())
                .orElseThrow(() -> new UcoParkingException("No se encontró el espacio de parqueadero."));

        parkingSpaceOutputPort.save(parkingSpace.occupy());

        return reservationOutputPort.save(reservation.occupy());
    }
}