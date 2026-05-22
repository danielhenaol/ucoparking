package co.edu.uco.ucoparking.infrastructure.persistence.repository.sql;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity.AcademicProgramJPAEntity;

public interface AcademicProgramJpaRepository extends JpaRepository<AcademicProgramJPAEntity, UUID> {
}
