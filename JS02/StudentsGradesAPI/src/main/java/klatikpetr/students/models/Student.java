package klatikpetr.students.models;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.intellij.lang.annotations.Pattern;
import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NotNull
    private Integer studentId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private Integer yearOfBirth;
    @Field("class")
    @NotNull
    private Byte studentClass;
}
