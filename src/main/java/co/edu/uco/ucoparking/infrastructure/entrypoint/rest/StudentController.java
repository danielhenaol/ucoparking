package co.edu.uco.ucoparking.infrastructure.entrypoint.rest;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.ucoparking.application.outputport.StudentOutputPort;
import co.edu.uco.ucoparking.crosscutting.exception.UcoParkingException;
import co.edu.uco.ucoparking.crosscutting.messagescatalog.MessagesEnum;
import co.edu.uco.ucoparking.domain.model.Student;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.RegisterNewStudentInput;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.dto.RegisterNewStudentDto;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.dto.StudentResponseDto;

/**
 * Controlador REST para probar estudiantes desde Bruno y desde el frontend.
 */
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final RegisterNewStudentInput registerNewStudentInput;
    private final StudentOutputPort studentOutputPort;

    public StudentController(RegisterNewStudentInput registerNewStudentInput, StudentOutputPort studentOutputPort) {
        this.registerNewStudentInput = registerNewStudentInput;
        this.studentOutputPort = studentOutputPort;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> create(@RequestBody RegisterNewStudentDto request) {
        var response = registerNewStudentInput.execute(request);
        return ResponseEntity.created(URI.create("/api/v1/students/" + response.getId())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> findAll() {
        var response = studentOutputPort.findAll()
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> findById(@PathVariable UUID id) {
        var student = studentOutputPort.findById(id)
                .orElseThrow(() -> new UcoParkingException(MessagesEnum.STUDENT_NOT_FOUND.getMessage()));

        return ResponseEntity.ok(toResponse(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> update(@PathVariable UUID id,
                                                     @RequestBody RegisterNewStudentDto request) {
        var student = new Student(
                id,
                request.getIdentification(),
                request.getInstitutionalCode(),
                request.getName(),
                request.getLastName(),
                request.getEmail(),
                request.getMobileNumber()
        );

        var updated = studentOutputPort.update(id, student);
        return ResponseEntity.ok(toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        studentOutputPort.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private StudentResponseDto toResponse(Student student) {
        return new StudentResponseDto(
                student.getId(),
                student.getIdentification(),
                student.getInstitutionalCode(),
                student.getName(),
                student.getLastName(),
                student.getEmail(),
                student.getMobileNumber()
        );
    }
}