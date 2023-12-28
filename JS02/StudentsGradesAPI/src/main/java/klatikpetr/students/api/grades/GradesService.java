package klatikpetr.students.api.grades;

import klatikpetr.students.models.StudentGrades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.function.Consumer;

@Service
public class GradesService {
    @Autowired
    private GradesRepository gradesRepository;
    public StudentGrades findByStudentId(Integer studentId) {
        StudentGrades studentGrades = gradesRepository.findByStudentId(studentId);
        if (studentGrades == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        return studentGrades;
    }

    public void addSubject(Integer studentId, String subject) {
        updateStudentGrades(studentId, studentGrades -> {
            Map<String, List<Integer>> gradesMap = studentGrades.getGrades();

            if (gradesMap.containsKey(subject)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subject already exists");
            } else {
                gradesMap.put(subject, new ArrayList<>());
            }
        });
    }

    public void deleteSubject(Integer studentId, String subject) {
        updateStudentGrades(studentId, studentGrades -> {
            Map<String, List<Integer>> gradesMap = studentGrades.getGrades();

            if (gradesMap.containsKey(subject)) {
                gradesMap.remove(subject);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
            }
        });
    }

    public void addGrades(Integer studentId, String subject, List<Integer> newGrades) {
        updateStudentGrades(studentId, studentGrades -> {
            Map<String, List<Integer>> gradesMap = studentGrades.getGrades();

            if (gradesMap.containsKey(subject)) {
                gradesMap.get(subject).addAll(newGrades);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
            }
        });
    }

    public void deleteGrade(Integer studentId, String subject, Integer grade) {
        updateStudentGrades(studentId, studentGrades -> {
            List<Integer> gradesList = studentGrades.getGrades().get(subject);

            if (gradesList == null || !gradesList.remove(grade)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject or grade not found");
            }
        });
    }

    private void updateStudentGrades(Integer studentId, Consumer<StudentGrades> updateAction) {
        Optional<StudentGrades> optionalStudentGrades = Optional.ofNullable(gradesRepository.findByStudentId(studentId));

        optionalStudentGrades.ifPresent(studentGrades -> {
            updateAction.accept(studentGrades);
            gradesRepository.save(studentGrades);
        });
    }
}
