package klatikpetr.students;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> singleStudent(Integer studentId) {
        return studentRepository.findByStudentId(studentId);
    }
    public Optional<List<Student>> searchStudents (String keyword) {
        return  studentRepository.findByFirstNameContainingOrLastNameContaining(keyword, keyword);
    }
    public Student addStudent (Student student) {
        return studentRepository.save(student);
    }
    public void deleteStudent(Integer studentId) {
        studentRepository.deleteByStudentId(studentId);
    }
}
