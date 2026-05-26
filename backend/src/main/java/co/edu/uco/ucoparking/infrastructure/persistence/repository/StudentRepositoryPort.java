package co.edu.uco.ucoparking.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import co.edu.uco.ucoparking.domain.model.Student;

public interface StudentRepositoryPort {

    Student create(Student student);

    Optional<Student> findById(UUID id);

    List<Student> findAll();

    Student update(UUID id, Student student);

    void deleteById(UUID id);

    boolean existsByIdentification(String identification);
}
