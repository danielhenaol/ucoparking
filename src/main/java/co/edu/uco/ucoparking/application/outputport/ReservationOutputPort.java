package co.edu.uco.ucoparking.application.outputport;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import co.edu.uco.ucoparking.domain.model.Reservation;
import co.edu.uco.ucoparking.domain.model.ReservationStatus;

public interface ReservationOutputPort {

    Reservation save(Reservation reservation);

    Optional<Reservation> findById(UUID id);

    List<Reservation> findAll();

    List<Reservation> findByStatus(ReservationStatus status);
}