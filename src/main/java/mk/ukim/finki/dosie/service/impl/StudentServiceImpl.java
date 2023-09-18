package mk.ukim.finki.dosie.service.impl;

import mk.ukim.finki.dosie.model.Course;
import mk.ukim.finki.dosie.model.Student;
import mk.ukim.finki.dosie.model.enums.ReportType;
import mk.ukim.finki.dosie.model.exceptions.CourseNotFoundException;
import mk.ukim.finki.dosie.model.exceptions.StudentNotFoundException;
import mk.ukim.finki.dosie.repository.CourseRepository;
import mk.ukim.finki.dosie.repository.ReportRepository;
import mk.ukim.finki.dosie.repository.StudentRepository;
import mk.ukim.finki.dosie.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final ReportRepository reportRepository;
    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    public StudentServiceImpl(ReportRepository reportRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.reportRepository = reportRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Student findStudentByIndex(String index) throws StudentNotFoundException {
        return this.studentRepository.findById(index).orElseThrow(() -> new StudentNotFoundException("Student cannot be found"));
    }

    @Override
    public List<Student> findAllStudents() {
        return this.studentRepository.findAll();
    }

    @Override
    public List<Student> findAllStudentsPositiveReport() {
        return this.reportRepository.findAll()
                .stream()
                .filter(report -> report.getCategory().getReportType().equals(ReportType.POSITIVE))
                .map(report -> report.getStudent())
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findAllStudentNegativeReport() {
        return this.reportRepository.findAll()
                .stream()
                .filter(report -> report.getCategory().getReportType().equals(ReportType.NEGATIVE))
                .map(report -> report.getStudent())
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findAllStudentPositiveReportOnCourse(Long courseId) throws CourseNotFoundException {
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course cannot be found"));
        return this.reportRepository.findAllByCourse(course)
                .stream()
                .filter(report -> report.getCategory().getReportType().equals(ReportType.POSITIVE))
                .map(report -> report.getStudent())
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findAllStudentNegativeReportOnCourse(Long courseId) throws CourseNotFoundException {
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course cannot be found"));
        return this.reportRepository.findAllByCourse(course)
                .stream()
                .filter(report -> report.getCategory().getReportType().equals(ReportType.POSITIVE))
                .map(report -> report.getStudent())
                .collect(Collectors.toList());
    }
}
