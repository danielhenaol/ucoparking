package co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uco.ucoparking.application.outputport.StudentOutputPort;
import co.edu.uco.ucoparking.crosscutting.exception.UcoParkingException;
import co.edu.uco.ucoparking.crosscutting.messagescatalog.MessagesEnum;
import co.edu.uco.ucoparking.domain.model.Student;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.domain.RegisterNewStudentDomain;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.RegisterNewStudentUseCase;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.mapper.RegisterNewStudentUseCaseMapper;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.validator.RegisterNewStudentValidator;

@Service
public class RegisterNewStudentUseCaseImpl implements RegisterNewStudentUseCase {

    private final StudentOutputPort studentOutputPort;
    private final RegisterNewStudentValidator validator;
    private final RegisterNewStudentUseCaseMapper useCaseMapper;

    public RegisterNewStudentUseCaseImpl(StudentOutputPort studentOutputPort,
                                         RegisterNewStudentValidator validator,
                                         RegisterNewStudentUseCaseMapper useCaseMapper) {
        this.studentOutputPort = studentOutputPort;
        this.validator = validator;
        this.useCaseMapper = useCaseMapper;
    }

    @Override
    @Transactional
    public Student execute(RegisterNewStudentDomain domain) {
        validator.validate(domain);

        Student student = useCaseMapper.toStudent(domain);

        if (studentOutputPort.existsByIdentification(student.getIdentification())) {
            throw new UcoParkingException(MessagesEnum.STUDENT_ALREADY_EXISTS.getMessage());
        }

        return studentOutputPort.create(student);
    }
}