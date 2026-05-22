package co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport;

import co.edu.uco.ucoparking.application.inputport.InputPort;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.dto.RegisterNewStudentDto;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.dto.StudentResponseDto;

public interface RegisterNewStudentInput extends InputPort<RegisterNewStudentDto, StudentResponseDto> {
}
