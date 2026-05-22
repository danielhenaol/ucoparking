package co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.mapper;

import java.util.UUID;
import org.springframework.stereotype.Component;
import co.edu.uco.ucoparking.domain.model.Student;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.domain.RegisterNewStudentDomain;

/**
 * Mapper del caso de uso.
 * Convierte RegisterNewStudentDomain ↔ Student (modelo global de dominio).
 */
@Component
public class RegisterNewStudentUseCaseMapper {

    /** Domain → Student (para guardar en BD) */
    public Student toStudent(RegisterNewStudentDomain domain) {
        return new Student(
                UUID.randomUUID(),
                domain.getIdentification(),
                domain.getInstitutionalCode(),
                domain.getName(),
                domain.getLastName(),
                domain.getEmail(),
                domain.getMobileNumber()
        );
    }

    /** Student → Domain (para responder) */
    public RegisterNewStudentDomain toDomain(Student student) {
        return new RegisterNewStudentDomain(
                student.getIdentification(),
                student.getInstitutionalCode(),
                student.getName(),
                student.getLastName(),
                student.getEmail(),
                student.getMobileNumber()
        );
    }
}