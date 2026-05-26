package co.edu.uco.ucoparking.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import co.edu.uco.ucoparking.domain.model.Student;
import co.edu.uco.ucoparking.infrastructure.persistence.repository.sql.entity.StudentJPAEntity;

@Component
public class StudentMapperJPA implements MapperJPA<Student, StudentJPAEntity> {

    @Override
    public StudentJPAEntity toJpaEntity(Student domain) {
        return new StudentJPAEntity(
                domain.getId(),
                domain.getIdentification(),
                domain.getInstitutionalCode(),
                domain.getName(),
                domain.getLastName(),
                domain.getEmail(),
                domain.getMobileNumber()
        );
    }

    @Override
    public Student toDomainEntity(StudentJPAEntity jpaEntity) {
        return new Student(
                jpaEntity.getId(),
                jpaEntity.getIdentification(),
                jpaEntity.getInstitutionalCode(),
                jpaEntity.getName(),
                jpaEntity.getLastName(),
                jpaEntity.getEmail(),
                jpaEntity.getMobileNumber()
        );
    }
}