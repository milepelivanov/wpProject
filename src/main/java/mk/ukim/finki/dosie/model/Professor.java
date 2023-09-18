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
public class Professor {

    @Id
    private String username;

    private String password;

    private String name;
    private String surname;

    @ManyToMany
    private List<Student> students;

    @ManyToMany
    private List<Course> courses;

    public Professor(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
