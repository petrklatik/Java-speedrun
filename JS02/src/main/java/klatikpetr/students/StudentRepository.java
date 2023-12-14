package klatikpetr.students;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, ObjectId> {
    Optional<Student> findByStudentId (Integer studentId);
    Optional<List<Student>> findByFirstNameContainingOrLastNameContaining (String firstName, String lastName);
    void deleteByStudentId(Integer studentId);
}
