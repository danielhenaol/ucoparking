package co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.mapper;

import org.springframework.stereotype.Component;
import co.edu.uco.ucoparking.crosscutting.helper.TextHelper;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.dto.RegisterNewStudentDto;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.dto.StudentResponseDto;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.domain.RegisterNewStudentDomain;


@Component
public class RegisterNewStudentMapper {


    public RegisterNewStudentDomain toDomain(RegisterNewStudentDto dto) {
        return new RegisterNewStudentDomain(
                TextHelper.trim(dto.getIdentification()),
                TextHelper.trim(dto.getInstitutionalCode()),
                TextHelper.trim(dto.getName()),
                TextHelper.trim(dto.getLastName()),
                TextHelper.trim(dto.getEmail()),
                TextHelper.trim(dto.getMobileNumber())
        );
    }


    public StudentResponseDto toResponse(RegisterNewStudentDomain domain) {
        return new StudentResponseDto(
                null,
                domain.getIdentification(),
                domain.getInstitutionalCode(),
                domain.getName(),
                domain.getLastName(),
                domain.getEmail(),
                domain.getMobileNumber()
        );
    }
}