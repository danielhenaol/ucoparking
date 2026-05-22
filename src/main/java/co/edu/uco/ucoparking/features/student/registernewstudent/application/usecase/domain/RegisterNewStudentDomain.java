package co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.domain;

/**
 * Objeto de dominio específico del caso de uso RegisterNewStudent.
 * Viaja desde el inputport hasta el usecase sin depender del modelo global.
 */
public class RegisterNewStudentDomain {

    private String identification;
    private String institutionalCode;
    private String name;
    private String lastName;
    private String email;
    private String mobileNumber;

    public RegisterNewStudentDomain() {
    }

    public RegisterNewStudentDomain(String identification, String institutionalCode,
                                    String name, String lastName, String email, String mobileNumber) {
        this.identification = identification;
        this.institutionalCode = institutionalCode;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getIdentification()     { return identification; }
    public String getInstitutionalCode()  { return institutionalCode; }
    public String getName()               { return name; }
    public String getLastName()           { return lastName; }
    public String getEmail()              { return email; }
    public String getMobileNumber()       { return mobileNumber; }

    public void setIdentification(String identification)       { this.identification = identification; }
    public void setInstitutionalCode(String institutionalCode)  { this.institutionalCode = institutionalCode; }
    public void setName(String name)                           { this.name = name; }
    public void setLastName(String lastName)                   { this.lastName = lastName; }
    public void setEmail(String email)                         { this.email = email; }
    public void setMobileNumber(String mobileNumber)           { this.mobileNumber = mobileNumber; }
}
