package klatikpetr.students.api.grades;

import klatikpetr.students.models.StudentGrades;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradesRepository extends MongoRepository<StudentGrades, ObjectId> {
    StudentGrades findByStudentId(Integer studentId);
    void deleteByStudentId(Integer studentId);
}
