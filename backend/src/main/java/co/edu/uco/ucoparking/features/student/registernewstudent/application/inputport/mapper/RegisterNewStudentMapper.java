package co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.mapper;

import org.springframework.stereotype.Component;

import co.edu.uco.ucoparking.crosscutting.exception.UcoParkingException;
import co.edu.uco.ucoparking.crosscutting.helper.TextHelper;
import co.edu.uco.ucoparking.domain.model.Student;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.dto.RegisterNewStudentDto;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.dto.StudentResponseDto;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.domain.RegisterNewStudentDomain;

@Component
public class RegisterNewStudentMapper {

    public RegisterNewStudentDomain toDomain(RegisterNewStudentDto dto) {
        if (dto == null) {
            throw new UcoParkingException("Los datos del estudiante no pueden ser nulos.");
        }

        return new RegisterNewStudentDomain(
                TextHelper.trim(dto.getIdentification()),
                TextHelper.trim(dto.getInstitutionalCode()),
                TextHelper.trim(dto.getName()),
                TextHelper.trim(dto.getLastName()),
                TextHelper.trim(dto.getEmail()),
                TextHelper.trim(dto.getMobileNumber())
        );
    }

    public StudentResponseDto toResponse(Student student) {
        if (student == null) {
            throw new UcoParkingException("El estudiante guardado no puede ser nulo.");
        }

        return new StudentResponseDto(
                student.getId(),
                student.getIdentification(),
                student.getInstitutionalCode(),
                student.getName(),
                student.getLastName(),
                student.getEmail(),
                student.getMobileNumber()
        );
    }
}