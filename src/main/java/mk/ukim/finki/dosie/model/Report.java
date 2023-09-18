package mk.ukim.finki.dosie.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.dosie.model.enums.ReportType;


import javax.security.auth.Subject;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private Integer degree;
    @Column(length = 1000)
    private String note;
    @ManyToOne
    private Professor professor;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Category category;

    public Report(Integer degree, String note, Professor professor, Student student, Course course, Category category) {
        this.degree = degree;
        this.note = note;
        this.professor = professor;
        this.student = student;
        this.course = course;
        this.category = category;
    }

}
