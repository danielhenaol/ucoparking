package co.edu.uco.ucoparking.infrastructure.persistence.repository.sql;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity.StudentJPAEntity;

@Repository
public interface StudentJpaRepository extends JpaRepository<StudentJPAEntity, UUID> {

    boolean existsByIdentification(String identification);
}