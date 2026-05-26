package co.edu.uco.ucoparking.features.parking.listspaces.application.dto;

import java.util.UUID;

import co.edu.uco.ucoparking.domain.model.ParkingSpaceStatus;

public record ParkingSpaceResponseDto(
        UUID id,
        String code,
        ParkingSpaceStatus status
) {
}