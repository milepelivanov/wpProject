package mk.ukim.finki.dosie.service;

import mk.ukim.finki.dosie.model.Report;
import mk.ukim.finki.dosie.model.exceptions.*;

import java.util.List;

public interface ReportService {

    Report createNewReport(Integer degree, String note, String professorUsername, String studentIndex, Long CourseId, Long categoryId)
            throws StudentNotFoundException, ProfessorNotFoundException, CourseNotFoundException, CategoryNotFoundException;

    List<Report> findAllReports();

    List<Report> findAllReportsForStudent(String index) throws StudentNotFoundException;

    List<Report> findAllPositiveReportsForStudent(String index) throws StudentNotFoundException;

    List<Report> findAllNegativeReportsForStudent(String index) throws StudentNotFoundException;

    Report findReportById(Long id) throws ReportNotFoundException;

    Report updateReport(Long reportId, Integer degree, String note, String professorUsername, String studentIndex, Long CourseId, Long categoryId)
            throws ReportNotFoundException, StudentNotFoundException, ProfessorNotFoundException, CourseNotFoundException, CategoryNotFoundException;
}
