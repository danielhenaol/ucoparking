package co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.validator;

import org.springframework.stereotype.Component;
import co.edu.uco.ucoparking.application.usecase.validator.Validator;
import co.edu.uco.ucoparking.crosscutting.exception.UcoParkingException;
import co.edu.uco.ucoparking.crosscutting.helper.EmailHelper;
import co.edu.uco.ucoparking.crosscutting.helper.TextHelper;
import co.edu.uco.ucoparking.crosscutting.messagescatalog.MessagesEnum;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.domain.RegisterNewStudentDomain;

@Component
public class RegisterNewStudentValidator implements Validator<RegisterNewStudentDomain> {

    @Override
    public void validate(RegisterNewStudentDomain domain) {
        if (TextHelper.isNullOrBlank(domain.getIdentification())) {
            throw new UcoParkingException(MessagesEnum.STUDENT_IDENTIFICATION_REQUIRED.getMessage());
        }
        if (TextHelper.isNullOrBlank(domain.getInstitutionalCode())) {
            throw new UcoParkingException(MessagesEnum.STUDENT_INSTITUTIONAL_CODE_REQUIRED.getMessage());
        }
        if (TextHelper.isNullOrBlank(domain.getName())) {
            throw new UcoParkingException(MessagesEnum.STUDENT_NAME_REQUIRED.getMessage());
        }
        if (TextHelper.isNullOrBlank(domain.getLastName())) {
            throw new UcoParkingException(MessagesEnum.STUDENT_LAST_NAME_REQUIRED.getMessage());
        }
        if (TextHelper.isNullOrBlank(domain.getEmail())) {
            throw new UcoParkingException(MessagesEnum.STUDENT_EMAIL_REQUIRED.getMessage());
        }
        if (!EmailHelper.isValid(domain.getEmail())) {
            throw new UcoParkingException(MessagesEnum.STUDENT_EMAIL_INVALID.getMessage());
        }
        if (TextHelper.isNullOrBlank(domain.getMobileNumber())) {
            throw new UcoParkingException(MessagesEnum.STUDENT_MOBILE_NUMBER_REQUIRED.getMessage());
        }
    }
}