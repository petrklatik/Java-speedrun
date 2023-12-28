package klatikpetr.students.api.grades;

import klatikpetr.students.models.StudentGrades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/{studentId}")
@CrossOrigin(origins = "*")
public class GradesController {
    @Autowired
    private GradesService gradesService;

    @GetMapping("/all-grades")
    public ResponseEntity<StudentGrades> getGradesByStudentId(@PathVariable Integer studentId) {
        StudentGrades studentGrades = gradesService.findByStudentId(studentId);
        return ResponseEntity.ok(studentGrades);
    }

    @PostMapping("/subjects/{subject}")
    public ResponseEntity<Void> addSubject(@PathVariable Integer studentId, @PathVariable String subject) {
        gradesService.addSubject(studentId, subject);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-subjects/{subject}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Integer studentId, @PathVariable String subject) {
        gradesService.deleteSubject(studentId, subject);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/subjects/{subject}/grades")
    public ResponseEntity<Void> addGrade(@PathVariable Integer studentId, @PathVariable String subject, @RequestBody List<Integer> newGrades) {
        gradesService.addGrades(studentId, subject, newGrades);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/subjects/{subject}/delete-grades/{grade}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Integer studentId, @PathVariable String subject, @PathVariable Integer grade) {
        gradesService.deleteGrade(studentId, subject, grade);
        return ResponseEntity.ok().build();
    }
}
