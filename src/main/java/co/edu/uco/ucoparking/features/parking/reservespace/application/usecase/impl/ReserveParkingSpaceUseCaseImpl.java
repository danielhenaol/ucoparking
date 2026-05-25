package co.edu.uco.ucoparking.features.parking.reservespace.application.usecase.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uco.ucoparking.application.outputport.ParkingSpaceOutputPort;
import co.edu.uco.ucoparking.application.outputport.ReservationOutputPort;
import co.edu.uco.ucoparking.crosscutting.exception.UcoParkingException;
import co.edu.uco.ucoparking.domain.model.ParkingSpaceStatus;
import co.edu.uco.ucoparking.domain.model.Reservation;
import co.edu.uco.ucoparking.domain.model.ReservationStatus;
import co.edu.uco.ucoparking.features.parking.reservespace.application.dto.ReserveParkingSpaceRequestDto;
import co.edu.uco.ucoparking.features.parking.reservespace.application.usecase.ReserveParkingSpaceUseCase;

/**
 * Implementación del caso de uso para reservar un espacio de parqueadero.
 */
@Service
public class ReserveParkingSpaceUseCaseImpl implements ReserveParkingSpaceUseCase {

    private final ParkingSpaceOutputPort parkingSpaceOutputPort;
    private final ReservationOutputPort reservationOutputPort;

    public ReserveParkingSpaceUseCaseImpl(ParkingSpaceOutputPort parkingSpaceOutputPort,
                                          ReservationOutputPort reservationOutputPort) {
        this.parkingSpaceOutputPort = parkingSpaceOutputPort;
        this.reservationOutputPort = reservationOutputPort;
    }

    @Override
    @Transactional
    public Reservation execute(ReserveParkingSpaceRequestDto request) {
        validateRequest(request);

        var parkingSpace = parkingSpaceOutputPort.findById(request.getParkingSpaceId())
                .orElseThrow(() -> new UcoParkingException("No se encontró el espacio de parqueadero."));

        if (parkingSpace.getStatus() != ParkingSpaceStatus.AVAILABLE) {
            throw new UcoParkingException("El espacio no está disponible para reservar.");
        }

        var reservationTime = LocalDateTime.now();
        var expirationTime = reservationTime.plusMinutes(30);

        var reservation = new Reservation(
                UUID.randomUUID(),
                parkingSpace.getId(),
                request.getVehiclePlate(),
                request.getEntryTime(),
                request.getExitTime(),
                reservationTime,
                expirationTime,
                ReservationStatus.ACTIVE
        );

        parkingSpaceOutputPort.save(parkingSpace.reserve());

        return reservationOutputPort.save(reservation);
    }

    private void validateRequest(ReserveParkingSpaceRequestDto request) {
        if (request == null) {
            throw new UcoParkingException("Los datos de la reserva son obligatorios.");
        }

        if (request.getParkingSpaceId() == null) {
            throw new UcoParkingException("El espacio de parqueadero es obligatorio.");
        }

        if (request.getVehiclePlate() == null || request.getVehiclePlate().trim().isEmpty()) {
            throw new UcoParkingException("La placa del vehículo es obligatoria.");
        }

        if (request.getEntryTime() == null) {
            throw new UcoParkingException("La hora de entrada es obligatoria.");
        }

        if (request.getExitTime() == null) {
            throw new UcoParkingException("La hora de salida es obligatoria.");
        }

        if (!request.getExitTime().isAfter(request.getEntryTime())) {
            throw new UcoParkingException("La hora de salida debe ser posterior a la hora de entrada.");
        }
    }
}