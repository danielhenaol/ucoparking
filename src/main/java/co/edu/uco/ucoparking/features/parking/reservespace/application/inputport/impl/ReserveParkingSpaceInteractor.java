package co.edu.uco.ucoparking.features.parking.reservespace.application.inputport.impl;

import org.springframework.stereotype.Service;

import co.edu.uco.ucoparking.application.inputport.ReserveParkingSpaceInputPort;
import co.edu.uco.ucoparking.domain.model.Reservation;
import co.edu.uco.ucoparking.features.parking.reservespace.application.dto.ReserveParkingSpaceRequestDto;
import co.edu.uco.ucoparking.features.parking.reservespace.application.usecase.ReserveParkingSpaceUseCase;

/**
 * Interactor que conecta el controlador con el caso de uso de reserva.
 */
@Service
public class ReserveParkingSpaceInteractor implements ReserveParkingSpaceInputPort {

    private final ReserveParkingSpaceUseCase reserveParkingSpaceUseCase;

    public ReserveParkingSpaceInteractor(ReserveParkingSpaceUseCase reserveParkingSpaceUseCase) {
        this.reserveParkingSpaceUseCase = reserveParkingSpaceUseCase;
    }

    @Override
    public Reservation execute(ReserveParkingSpaceRequestDto request) {
        return reserveParkingSpaceUseCase.execute(request);
    }
}