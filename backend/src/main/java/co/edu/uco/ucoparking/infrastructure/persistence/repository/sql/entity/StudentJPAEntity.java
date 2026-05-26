package co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class StudentJPAEntity {

    @Id
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "identification", nullable = false, unique = true, length = 30)
    private String identification;

    @Column(name = "institutional_code", nullable = false, length = 30)
    private String institutionalCode;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @Column(name = "email", nullable = false, length = 120)
    private String email;

    @Column(name = "mobile_number", nullable = false, length = 30)
    private String mobileNumber;

    public StudentJPAEntity() {
    }

    public StudentJPAEntity(UUID id, String identification, String institutionalCode, String name,
                            String lastName, String email, String mobileNumber) {
        this.id = id;
        this.identification = identification;
        this.institutionalCode = institutionalCode;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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