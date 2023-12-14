package klatikpetr.students;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.index.IndexOptions;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Year;

@Document (collection = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private Integer studentId;
    private String firstName;
    private String lastName;
    private Integer yearOfBirth;
    @Field("class")
    private Byte studentClass;
}
