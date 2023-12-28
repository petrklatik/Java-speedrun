package klatikpetr.students.api.students;

import klatikpetr.students.api.grades.GradesRepository;
import klatikpetr.students.models.Student;
import klatikpetr.students.models.StudentGrades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GradesRepository gradesRepository;

    public List<Student> allStudents() {
        return studentRepository.findAll();
    }

    public Student singleStudent(Integer studentId) {
        return studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }
    public Optional<List<Student>> searchStudents (String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Optional.empty();
        }
        return studentRepository.findByFirstNameContainingOrLastNameContaining(keyword, keyword);
    }
    public Student addStudent (Student student) {
        Optional<Student> existingStudent = studentRepository.findByStudentId(student.getStudentId());
        if (existingStudent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student with the same studentId already exists");
        }

        StudentGrades studentGrades = new StudentGrades();
        studentGrades.setStudentId(studentRepository.save(student).getStudentId());
        studentGrades.setGrades(new HashMap<>());

        gradesRepository.save(studentGrades);

        return studentRepository.save(student);
    }
    public void deleteStudent(Integer studentId) {
        Optional<Student> existingStudent = studentRepository.findByStudentId(studentId);
        if (existingStudent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        gradesRepository.deleteByStudentId(studentId);
        studentRepository.deleteByStudentId(studentId);
    }
}
