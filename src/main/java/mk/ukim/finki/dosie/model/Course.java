package mk.ukim.finki.dosie.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseName;

    @ManyToMany(mappedBy = "courses")
    private List<Professor> professors;

    public Course(String courseName, List<Professor> professors) {
        this.courseName = courseName;
        this.professors = professors;
    }
}
