package co.edu.uco.ucoparking.infrastructure.entrypoint.rest;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.ucoparking.application.inputport.OccupyParkingSpaceInputPort;
import co.edu.uco.ucoparking.application.inputport.ReserveParkingSpaceInputPort;
import co.edu.uco.ucoparking.domain.model.Reservation;
import co.edu.uco.ucoparking.features.parking.reservespace.application.dto.ReservationResponseDto;
import co.edu.uco.ucoparking.features.parking.reservespace.application.dto.ReserveParkingSpaceRequestDto;

@RestController
@RequestMapping("/api/v1/parking/reservations")
public class ReservationController {

    private final ReserveParkingSpaceInputPort reserveParkingSpaceInputPort;
    private final OccupyParkingSpaceInputPort occupyParkingSpaceInputPort;

    public ReservationController(ReserveParkingSpaceInputPort reserveParkingSpaceInputPort,
                                 OccupyParkingSpaceInputPort occupyParkingSpaceInputPort) {
        this.reserveParkingSpaceInputPort = reserveParkingSpaceInputPort;
        this.occupyParkingSpaceInputPort = occupyParkingSpaceInputPort;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> reserve(@RequestBody ReserveParkingSpaceRequestDto request) {
        var reservation = reserveParkingSpaceInputPort.execute(request);

        return ResponseEntity
                .created(URI.create("/api/v1/parking/reservations/" + reservation.getId()))
                .body(toResponse(reservation));
    }

    @PutMapping("/{id}/occupy")
    public ResponseEntity<ReservationResponseDto> occupy(@PathVariable UUID id) {
        var reservation = occupyParkingSpaceInputPort.execute(id);
        return ResponseEntity.ok(toResponse(reservation));
    }

    private ReservationResponseDto toResponse(Reservation reservation) {
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