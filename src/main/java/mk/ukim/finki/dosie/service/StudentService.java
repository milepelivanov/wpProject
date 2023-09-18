package mk.ukim.finki.dosie.service;

import mk.ukim.finki.dosie.model.Student;
import mk.ukim.finki.dosie.model.exceptions.CourseNotFoundException;
import mk.ukim.finki.dosie.model.exceptions.StudentNotFoundException;

import java.util.List;

public interface StudentService {

    Student findStudentByIndex(String index) throws StudentNotFoundException;
    List<Student> findAllStudents();
    List<Student> findAllStudentsPositiveReport();
    List<Student> findAllStudentNegativeReport();
    List<Student> findAllStudentPositiveReportOnCourse(Long courseId) throws CourseNotFoundException;
    List<Student> findAllStudentNegativeReportOnCourse(Long courseId) throws CourseNotFoundException;

}
