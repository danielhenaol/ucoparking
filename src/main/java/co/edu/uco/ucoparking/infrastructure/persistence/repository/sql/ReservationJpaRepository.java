package co.edu.uco.ucoparking.infrastructure.persistence.repository.sql;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uco.ucoparking.domain.model.ReservationStatus;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity.ReservationJPAEntity;

/**
 * Repositorio JPA para gestionar las reservas en la base de datos.
 */
@Repository
public interface ReservationJpaRepository extends JpaRepository<ReservationJPAEntity, UUID> {

    /**
     * Busca reservas según su estado.
     *
     * @param status estado de la reserva.
     * @return lista de reservas que coinciden con el estado.
     */
    List<ReservationJPAEntity> findByStatus(ReservationStatus status);
}