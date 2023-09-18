package mk.ukim.finki.dosie.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    @Id
    private String index;

    private String name;

    private String surname;

    @ManyToMany(mappedBy = "students")
    private List<Professor> professors;

    public Student(String index, String name, String surname) {
        this.index = index;
        this.name = name;
        this.surname = surname;
    }
}
