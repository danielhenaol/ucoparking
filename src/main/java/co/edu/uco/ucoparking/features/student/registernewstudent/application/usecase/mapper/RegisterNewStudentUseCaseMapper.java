package co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import co.edu.uco.ucoparking.crosscutting.exception.UcoParkingException;
import co.edu.uco.ucoparking.domain.model.Student;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.domain.RegisterNewStudentDomain;

@Component
public class RegisterNewStudentUseCaseMapper {

    public Student toStudent(RegisterNewStudentDomain domain) {
        if (domain == null) {
            throw new UcoParkingException("El dominio de registro de estudiante no puede ser nulo.");
        }

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

    public RegisterNewStudentDomain toDomain(Student student) {
        if (student == null) {
            throw new UcoParkingException("El estudiante no puede ser nulo.");
        }

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