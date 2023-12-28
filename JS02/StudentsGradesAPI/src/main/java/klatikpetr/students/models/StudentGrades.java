package klatikpetr.students.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
@Document(collection = "grades")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGrades {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private Integer studentId;
    private Map<String, List<Integer>> grades;

}
