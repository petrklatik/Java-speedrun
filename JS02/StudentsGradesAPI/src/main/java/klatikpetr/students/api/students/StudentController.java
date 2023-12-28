package klatikpetr.students.api.students;

import klatikpetr.students.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/get-all-students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.allStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getSingleStudent(@PathVariable Integer studentId) {
        Student student = studentService.singleStudent(studentId);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/search")
    public ResponseEntity<Optional<List<Student>>> searchStudents(@RequestParam String keyword) {
        Optional<List<Student>> foundStudents = studentService.searchStudents(keyword.trim());
        return ResponseEntity.ok(foundStudents);
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody @Validated Student student) {
        Student newStudent = studentService.addStudent(student);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }
}
