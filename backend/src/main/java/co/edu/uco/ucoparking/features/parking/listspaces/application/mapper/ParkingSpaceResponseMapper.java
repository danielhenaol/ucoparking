package co.edu.uco.ucoparking.features.parking.listspaces.application.mapper;

import org.springframework.stereotype.Component;

import co.edu.uco.ucoparking.crosscutting.exception.UcoParkingException;
import co.edu.uco.ucoparking.domain.model.ParkingSpace;
import co.edu.uco.ucoparking.features.parking.listspaces.application.dto.ParkingSpaceResponseDto;

@Component
public class ParkingSpaceResponseMapper {

    public ParkingSpaceResponseDto toResponse(ParkingSpace parkingSpace) {
        if (parkingSpace == null) {
            throw new UcoParkingException("El espacio de parqueadero no puede ser nulo para construir la respuesta.");
        }

        return new ParkingSpaceResponseDto(
                parkingSpace.getId(),
                parkingSpace.getCode(),
                parkingSpace.getStatus()
        );
    }
}