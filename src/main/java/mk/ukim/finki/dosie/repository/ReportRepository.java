package mk.ukim.finki.dosie.repository;

import mk.ukim.finki.dosie.model.Course;
import mk.ukim.finki.dosie.model.Report;

import mk.ukim.finki.dosie.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByStudent(Student student);

    List<Report> findAllByCourse(Course course);

}
