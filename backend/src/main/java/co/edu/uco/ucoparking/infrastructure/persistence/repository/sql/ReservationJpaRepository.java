package co.edu.uco.ucoparking.infrastructure.persistence.repository.sql;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uco.ucoparking.domain.model.ReservationStatus;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity.ReservationJPAEntity;

@Repository
public interface ReservationJpaRepository extends JpaRepository<ReservationJPAEntity, UUID> {


    List<ReservationJPAEntity> findByStatus(ReservationStatus status);
}