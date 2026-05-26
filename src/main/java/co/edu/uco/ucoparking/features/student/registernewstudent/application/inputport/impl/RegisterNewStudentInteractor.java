package co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.impl;

import org.springframework.stereotype.Service;
import co.edu.uco.ucoparking.domain.model.Student;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.RegisterNewStudentInput;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.dto.RegisterNewStudentDto;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.dto.StudentResponseDto;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.mapper.RegisterNewStudentMapper;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.RegisterNewStudentUseCase;

@Service
public class RegisterNewStudentInteractor implements RegisterNewStudentInput {

    private final RegisterNewStudentUseCase registerNewStudentUseCase;
    private final RegisterNewStudentMapper mapper;

    public RegisterNewStudentInteractor(RegisterNewStudentUseCase registerNewStudentUseCase,
                                        RegisterNewStudentMapper mapper) {
        this.registerNewStudentUseCase = registerNewStudentUseCase;
        this.mapper = mapper;
    }

    @Override
    public StudentResponseDto execute(RegisterNewStudentDto data) {
        var domain = mapper.toDomain(data);
        var savedStudent = registerNewStudentUseCase.execute(domain);

        return buildResponse(savedStudent);
    }

    private StudentResponseDto buildResponse(Student student) {
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