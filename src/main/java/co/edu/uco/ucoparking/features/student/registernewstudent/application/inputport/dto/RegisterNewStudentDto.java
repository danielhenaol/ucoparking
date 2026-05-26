package co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.dto;

public class RegisterNewStudentDto {

    private String identification;
    private String institutionalCode;
    private String name;
    private String lastName;
    private String email;
    private String mobileNumber;

    public RegisterNewStudentDto() {
    }

    public RegisterNewStudentDto(String identification, String institutionalCode, String name, String lastName,
                                 String email, String mobileNumber) {
        this.identification = identification;
        this.institutionalCode = institutionalCode;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getInstitutionalCode() {
        return institutionalCode;
    }

    public void setInstitutionalCode(String institutionalCode) {
        this.institutionalCode = institutionalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}