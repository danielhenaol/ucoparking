package co.edu.uco.ucoparking.application.inputport.mapper;

public interface MapperDTO<D, DTO> {

    D toDomain(DTO dto);

    DTO toDto(D domain);
}
