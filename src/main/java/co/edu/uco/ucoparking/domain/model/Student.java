package co.edu.uco.ucoparking.domain.model;

import java.util.UUID;

/**
 * Modelo de dominio que representa un estudiante dentro del negocio.
 *
 * En dominio, los atributos son privados, los getters públicos
 * y los setters privados para proteger el estado del objeto.
 */
public class Student {

    private UUID id;
    private String identification;
    private String institutionalCode;
    private String name;
    private String lastName;
    private String email;
    private String mobileNumber;

    public Student(UUID id, String identification, String institutionalCode, String name, String lastName,
                   String email, String mobileNumber) {
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

    public String getIdentification() {
        return identification;
    }

    public String getInstitutionalCode() {
        return institutionalCode;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    private void setId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El id del estudiante es obligatorio.");
        }
        this.id = id;
    }

    private void setIdentification(String identification) {
        if (identification == null || identification.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación del estudiante es obligatoria.");
        }
        this.identification = identification.trim();
    }

    private void setInstitutionalCode(String institutionalCode) {
        if (institutionalCode == null || institutionalCode.trim().isEmpty()) {
            throw new IllegalArgumentException("El código institucional es obligatorio.");
        }
        this.institutionalCode = institutionalCode.trim();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio.");
        }
        this.name = name.trim();
    }

    private void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del estudiante es obligatorio.");
        }
        this.lastName = lastName.trim();
    }

    private void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo del estudiante es obligatorio.");
        }
        this.email = email.trim();
    }

    private void setMobileNumber(String mobileNumber) {
        if (mobileNumber == null || mobileNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("El número celular del estudiante es obligatorio.");
        }
        this.mobileNumber = mobileNumber.trim();
    }
}