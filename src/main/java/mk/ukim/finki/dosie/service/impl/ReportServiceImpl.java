package mk.ukim.finki.dosie.service.impl;

import mk.ukim.finki.dosie.model.*;
import mk.ukim.finki.dosie.model.enums.ReportType;
import mk.ukim.finki.dosie.model.exceptions.*;
import mk.ukim.finki.dosie.repository.*;
import mk.ukim.finki.dosie.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    public ReportServiceImpl(ReportRepository reportRepository, StudentRepository studentRepository, ProfessorRepository professorRepository,
                             CourseRepository courseRepository, CategoryRepository categoryRepository) {
        this.reportRepository = reportRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Report createNewReport(Integer degree, String note, String professorUsername, String studentIndex, Long courseId, Long categoryId)
            throws StudentNotFoundException, ProfessorNotFoundException, CourseNotFoundException, CategoryNotFoundException {
        Professor professor = this.professorRepository.findById(professorUsername).orElseThrow(() -> new ProfessorNotFoundException("Professor cannot be found"));
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course cannot be found"));
        Student student = this.studentRepository.findById(studentIndex).orElseThrow(() -> new StudentNotFoundException("Student cannot be found"));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category cannot be found"));
        Report report = new Report(degree, note, professor, student, course, category);

        return this.reportRepository.save(report);
    }

    @Override
    public List<Report> findAllReports() {
        List<Report> allReports = reportRepository.findAll();
        Collections.reverse(allReports);
        return allReports;
    }

    @Override
    public List<Report> findAllReportsForStudent(String index) throws StudentNotFoundException {
        Student student = this.studentRepository.findById(index).orElseThrow(() -> new StudentNotFoundException("Student is not found"));
        return this.reportRepository.findAllByStudent(student);
    }

    @Override
    public List<Report> findAllPositiveReportsForStudent(String index) throws StudentNotFoundException {
        Student student = this.studentRepository.findById(index).orElseThrow(() -> new StudentNotFoundException("Student is not found"));
        return this.reportRepository
                .findAllByStudent(student)
                .stream()
                .filter(r -> r.getCategory().getReportType().equals(ReportType.POSITIVE))
                .collect(Collectors.toList());
    }

    @Override
    public List<Report> findAllNegativeReportsForStudent(String index) throws StudentNotFoundException {
        Student student = this.studentRepository.findById(index).orElseThrow(() -> new StudentNotFoundException("Student is not found"));
        return this.reportRepository
                .findAllByStudent(student)
                .stream()
                .filter(r -> r.getCategory().getReportType().equals(ReportType.NEGATIVE))
                .collect(Collectors.toList());
    }

    @Override
    public Report findReportById(Long id) throws ReportNotFoundException {
        return this.reportRepository.findById(id).orElseThrow(() -> new ReportNotFoundException("Report is not found"));
    }

    @Override
    public Report updateReport(Long reportId, Integer degree, String note, String professorUsername, String studentIndex, Long CourseId, Long categoryId) throws ReportNotFoundException, StudentNotFoundException, ProfessorNotFoundException, CourseNotFoundException, CategoryNotFoundException {
        Report report = this.reportRepository.findById(reportId).orElseThrow(() -> new ReportNotFoundException("Report is not found"));
        report.setDegree(degree);
        report.setNote(note);

        Professor professor = this.professorRepository.findById(professorUsername).orElseThrow(() -> new ProfessorNotFoundException("Professor not found"));
        report.setProfessor(professor);

        Student student = this.studentRepository.findById(studentIndex).orElseThrow(() -> new StudentNotFoundException("Student is not found"));
        report.setStudent(student);

        Course course = this.courseRepository.findById(CourseId).orElseThrow(() -> new CourseNotFoundException("Course is not found"));
        report.setCourse(course);

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category is not found"));
        report.setCategory(category);

        return this.reportRepository.save(report);
    }
}
