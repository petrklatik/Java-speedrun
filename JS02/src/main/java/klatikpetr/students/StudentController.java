package klatikpetr.students;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/get-all-students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.allStudents());
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<Optional<Student>> getSingleStudent(@PathVariable Integer studentId) {
        return ResponseEntity.ok(studentService.singleStudent(studentId));
    }
    @GetMapping("/search")
    public ResponseEntity<Optional<List<Student>>> searchPeople(@RequestParam String keyword) {
        return ResponseEntity.ok(studentService.searchStudents(keyword));
    }
    @PostMapping("/add")
    public ResponseEntity<Student> addStudent (@RequestBody Student student) {
        Student newStudent = studentService.addStudent(student);
        return ResponseEntity.ok(newStudent);
    }
    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<Void> deleteStudent (@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
