package co.edu.uco.ucoparking.infrastructure.entrypoint.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.ucoparking.application.inputport.ListParkingSpacesInputPort;
import co.edu.uco.ucoparking.domain.model.ParkingSpace;
import co.edu.uco.ucoparking.domain.model.ParkingSpaceStatus;

@RestController
@RequestMapping("/api/v1/parking/spaces")
public class ParkingSpaceController {

    private final ListParkingSpacesInputPort listParkingSpacesInputPort;

    public ParkingSpaceController(ListParkingSpacesInputPort listParkingSpacesInputPort) {
        this.listParkingSpacesInputPort = listParkingSpacesInputPort;
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpaceResponse>> findAll() {
        var response = listParkingSpacesInputPort.execute()
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/available")
    public ResponseEntity<List<ParkingSpaceResponse>> findAvailable() {
        return findByStatus(ParkingSpaceStatus.AVAILABLE);
    }

    @GetMapping("/reserved")
    public ResponseEntity<List<ParkingSpaceResponse>> findReserved() {
        return findByStatus(ParkingSpaceStatus.RESERVED);
    }

    @GetMapping("/occupied")
    public ResponseEntity<List<ParkingSpaceResponse>> findOccupied() {
        return findByStatus(ParkingSpaceStatus.OCCUPIED);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ParkingSpaceResponse>> findByStatus(@PathVariable ParkingSpaceStatus status) {
        var response = listParkingSpacesInputPort.findByStatus(status)
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    private ParkingSpaceResponse toResponse(ParkingSpace parkingSpace) {
        return new ParkingSpaceResponse(
                parkingSpace.getId(),
                parkingSpace.getCode(),
                parkingSpace.getStatus()
        );
    }

    public record ParkingSpaceResponse(
            UUID id,
            String code,
            ParkingSpaceStatus status
    ) {
    }
}