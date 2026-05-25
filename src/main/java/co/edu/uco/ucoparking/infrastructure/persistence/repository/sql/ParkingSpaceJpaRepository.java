package co.edu.uco.ucoparking.infrastructure.persistence.repository.sql;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uco.ucoparking.domain.model.ParkingSpaceStatus;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity.ParkingSpaceJPAEntity;

/**
 * Repositorio JPA para gestionar los espacios de parqueadero en la base de datos.
 */
@Repository
public interface ParkingSpaceJpaRepository extends JpaRepository<ParkingSpaceJPAEntity, UUID> {

    /**
     * Busca espacios de parqueadero según su estado.
     *
     * @param status estado del espacio.
     * @return lista de espacios que coinciden con el estado.
     */
    List<ParkingSpaceJPAEntity> findByStatus(ParkingSpaceStatus status);
}