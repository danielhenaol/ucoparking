package co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.dto;

import java.util.UUID;

import co.edu.uco.ucoparking.crosscutting.exception.UcoParkingException;

public class StudentResponseDto {

    private UUID id;
    private String identification;
    private String institutionalCode;
    private String name;
    private String lastName;
    private String email;
    private String mobileNumber;

    public StudentResponseDto() {
    }

    public StudentResponseDto(UUID id, String identification, String institutionalCode, String name,
                              String lastName, String email, String mobileNumber) {
        setId(id);
        setIdentification(identification);
        setInstitutionalCode(institutionalCode);
        setName(name);
        setLastName(lastName);
        setEmail(email);
        setMobileNumber(mobileNumber);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        if (id == null) {
            throw new UcoParkingException("El id del estudiante en la respuesta no puede ser nulo.");
        }
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        if (identification == null || identification.trim().isEmpty()) {
            throw new UcoParkingException("La identificación del estudiante en la respuesta no puede ser nula o vacía.");
        }
        this.identification = identification.trim();
    }

    public String getInstitutionalCode() {
        return institutionalCode;
    }

    public void setInstitutionalCode(String institutionalCode) {
        if (institutionalCode == null || institutionalCode.trim().isEmpty()) {
            throw new UcoParkingException("El código institucional en la respuesta no puede ser nulo o vacío.");
        }
        this.institutionalCode = institutionalCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new UcoParkingException("El nombre del estudiante en la respuesta no puede ser nulo o vacío.");
        }
        this.name = name.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new UcoParkingException("El apellido del estudiante en la respuesta no puede ser nulo o vacío.");
        }
        this.lastName = lastName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new UcoParkingException("El correo del estudiante en la respuesta no puede ser nulo o vacío.");
        }
        this.email = email.trim();
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        if (mobileNumber == null || mobileNumber.trim().isEmpty()) {
            throw new UcoParkingException("El celular del estudiante en la respuesta no puede ser nulo o vacío.");
        }
        this.mobileNumber = mobileNumber.trim();
    }
}