package co.edu.uco.ucoparking.infrastructure.persistence.repository.adapter.sql.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import co.edu.uco.ucoparking.application.outputport.StudentOutputPort;
import co.edu.uco.ucoparking.crosscutting.exception.UcoParkingException;
import co.edu.uco.ucoparking.crosscutting.messagescatalog.MessagesEnum;
import co.edu.uco.ucoparking.domain.model.Student;
import co.edu.uco.ucoparking.infrastructure.persistence.mapper.StudentMapperJPA;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.StudentRepositoryPort;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.StudentJpaRepository;


@Repository
public class StudentRepositoryJpaAdapter implements StudentOutputPort, StudentRepositoryPort {

    private final StudentJpaRepository studentJpaRepository;
    private final StudentMapperJPA studentMapperJPA;

    public StudentRepositoryJpaAdapter(StudentJpaRepository studentJpaRepository, StudentMapperJPA studentMapperJPA) {
        this.studentJpaRepository = studentJpaRepository;
        this.studentMapperJPA = studentMapperJPA;
    }

    @Override
    public Student create(Student student) {
        var saved = studentJpaRepository.save(studentMapperJPA.toJpaEntity(student));
        return studentMapperJPA.toDomainEntity(saved);
    }

    @Override
    public Optional<Student> findById(UUID id) {
        return studentJpaRepository.findById(id)
                .map(studentMapperJPA::toDomainEntity);
    }

    @Override
    public List<Student> findAll() {
        return studentJpaRepository.findAll()
                .stream()
                .map(studentMapperJPA::toDomainEntity)
                .toList();
    }

    @Override
    public Student update(UUID id, Student student) {
        if (!studentJpaRepository.existsById(id)) {
            throw new UcoParkingException(MessagesEnum.STUDENT_NOT_FOUND.getMessage());
        }

        var updated = new Student(
                id,
                student.getIdentification(),
                student.getInstitutionalCode(),
                student.getName(),
                student.getLastName(),
                student.getEmail(),
                student.getMobileNumber()
        );

        var saved = studentJpaRepository.save(studentMapperJPA.toJpaEntity(updated));
        return studentMapperJPA.toDomainEntity(saved);
    }

    @Override
    public void deleteById(UUID id) {
        if (!studentJpaRepository.existsById(id)) {
            throw new UcoParkingException(MessagesEnum.STUDENT_NOT_FOUND.getMessage());
        }

        studentJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByIdentification(String identification) {
        return studentJpaRepository.existsByIdentification(identification);
    }
}