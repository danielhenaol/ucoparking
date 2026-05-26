package co.edu.uco.ucoparking.application.inputport;

import co.edu.uco.ucoparking.domain.model.Reservation;
import co.edu.uco.ucoparking.features.parking.reservespace.application.dto.ReserveParkingSpaceRequestDto;

public interface ReserveParkingSpaceInputPort {

    Reservation execute(ReserveParkingSpaceRequestDto request);
}