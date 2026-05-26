package co.edu.uco.ucoparking.features.parking.reservespace.application.mapper;

import org.springframework.stereotype.Component;

import co.edu.uco.ucoparking.crosscutting.exception.UcoParkingException;
import co.edu.uco.ucoparking.domain.model.Reservation;
import co.edu.uco.ucoparking.features.parking.reservespace.application.dto.ReservationResponseDto;

@Component
public class ReservationResponseMapper {

    public ReservationResponseDto toResponse(Reservation reservation) {
        if (reservation == null) {
            throw new UcoParkingException("La reserva no puede ser nula para construir la respuesta.");
        }

        return new ReservationResponseDto(
                reservation.getId(),
                reservation.getParkingSpaceId(),
                reservation.getVehiclePlate(),
                reservation.getEntryTime(),
                reservation.getExitTime(),
                reservation.getReservationTime(),
                reservation.getExpirationTime(),
                reservation.getStatus()
        );
    }
}