package co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase;

import co.edu.uco.ucoparking.application.usecase.UseCase;
import co.edu.uco.ucoparking.domain.model.Student;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.domain.RegisterNewStudentDomain;

public interface RegisterNewStudentUseCase
        extends UseCase<RegisterNewStudentDomain, Student> {
}