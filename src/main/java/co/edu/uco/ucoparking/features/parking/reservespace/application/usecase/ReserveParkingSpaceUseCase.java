package co.edu.uco.ucoparking.features.parking.reservespace.application.usecase;

import co.edu.uco.ucoparking.domain.model.Reservation;
import co.edu.uco.ucoparking.features.parking.reservespace.application.dto.ReserveParkingSpaceRequestDto;

/**
 * Caso de uso para reservar un espacio.
 */
public interface ReserveParkingSpaceUseCase {

    Reservation execute(ReserveParkingSpaceRequestDto request);
}