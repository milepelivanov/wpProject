package mk.ukim.finki.dosie.web;


import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.dosie.model.*;
import mk.ukim.finki.dosie.model.enums.ReportType;
import mk.ukim.finki.dosie.model.exceptions.*;
import mk.ukim.finki.dosie.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ReportController {


    private final ReportService reportService;
    private final StudentService studentService;
    private final CourseService courseService;
    private final ProfessorService professorService;
    private final CategoryService categoryService;
    public boolean errorThrown = false;
    public boolean errorThrownOnUpdate = false;


    public ReportController(ReportService reportService, StudentService studentService, CourseService courseService, ProfessorService professorService, CategoryService categoryService) {
        this.reportService = reportService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.professorService = professorService;
        this.categoryService = categoryService;
    }


    @GetMapping({"/","reports"})
    public String getHomePage(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if(principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        }
        model.addAttribute("username", username);
        List<Report> allReports = reportService.findAllReports();
        model.addAttribute("allReports", allReports);
        return "home";
    }


    @PostMapping("showReports")
    public String showReports(@RequestParam(required = false) String index, Model model) throws StudentNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if(principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        }
        model.addAttribute("username", username);
        List<Report> reports;
        if (index == null || index.length() == 0)
            return "redirect:/reports";

        try {
            reports = reportService.findAllReportsForStudent(index);
        }catch (StudentNotFoundException studentNotFoundException){
            List<Report> allReports = reportService.findAllReports();
            model.addAttribute("allReports", allReports);
            model.addAttribute("studentNotFoundFromSearch",true);
            return "home";
        }
        model.addAttribute("reports", reports);
        return "reports";
    }

    @GetMapping("addForm")
    public String showAdd(Model model) {
        List<Course> courses = this.courseService.findAllCourses();
        List<Category> categories = this.categoryService.findAllCategories();

        model.addAttribute("courses", courses);
        model.addAttribute("categories", categories);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if(principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        }
        model.addAttribute("username", username);

        return "add-form";
    }

    @GetMapping("edit/{id}")
    public String showEdit(@PathVariable Long id, Model model) throws ReportNotFoundException {

        Report report = this.reportService.findReportById(id);
        List<Course> courses = this.courseService.findAllCourses();
        List<Category> categories = this.categoryService.findAllCategories();

        model.addAttribute("report", report);
        model.addAttribute("courses", courses);
        model.addAttribute("categories", categories);

        model.addAttribute("setFalse",false);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if(principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        }
        model.addAttribute("username", username);
        return "add-form";
    }

    @PostMapping("add/")
    public String addReport(@RequestParam String student_index,
                            @RequestParam String professor_username,
                            @RequestParam Long course,
                            @RequestParam Long category,
                            @RequestParam Integer degree,
                            @RequestParam String report_note,
                            Model model,
                            HttpServletResponse servletResponse
                            ) throws InterruptedException,CourseNotFoundException, ProfessorNotFoundException, CategoryNotFoundException, StudentNotFoundException {

        errorThrown = false;

        try {
                reportService.createNewReport(degree,report_note,professor_username,student_index,course,category);
            }catch (ProfessorNotFoundException professorNotFoundException){

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = "";
            if(principal instanceof UserDetails){
                username = ((UserDetails)principal).getUsername();
            }
            model.addAttribute("username", username);

                errorThrown = true;

                List<Course> courses = this.courseService.findAllCourses();
                List<Category> categories = this.categoryService.findAllCategories();

                model.addAttribute("courses", courses);
                model.addAttribute("categories", categories);

                model.addAttribute("professorError",professorNotFoundException.getMessage());


                return "add-form";
            }
            catch (StudentNotFoundException studentNotFoundException){

                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                String username = "";
                if(principal instanceof UserDetails){
                    username = ((UserDetails)principal).getUsername();
                }
                model.addAttribute("username", username);

                errorThrown = true;

                List<Course> courses = this.courseService.findAllCourses();
                List<Category> categories = this.categoryService.findAllCategories();

                model.addAttribute("courses", courses);
                model.addAttribute("categories", categories);

                model.addAttribute("studentError",studentNotFoundException.getMessage());

                return "add-form";
            }
        if(!errorThrown){

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = "";
            if(principal instanceof UserDetails){
                username = ((UserDetails)principal).getUsername();
            }
            model.addAttribute("username", username);

            model.addAttribute("reportCreated",true);

            List<Course> courses = this.courseService.findAllCourses();
            List<Category> categories = this.categoryService.findAllCategories();

            model.addAttribute("courses", courses);
            model.addAttribute("categories", categories);

            servletResponse.setHeader("Refresh", "2;url=/");
            return "add-form";
        }
        return "redirect:/reports";
    }

    @PostMapping("add/{id}")
    public String editReport(@PathVariable Long id,
                             @RequestParam String student_index_available,
                             @RequestParam String professor_username,
                             @RequestParam Long course,
                             @RequestParam Long category,
                             @RequestParam Integer degree,
                             Model model,
                             @RequestParam String report_note,
                             HttpServletResponse servletResponse) throws CourseNotFoundException, ProfessorNotFoundException, CategoryNotFoundException, StudentNotFoundException, ReportNotFoundException {

            errorThrownOnUpdate = false;

        try {
            this.reportService.updateReport(id, degree, report_note, professor_username, student_index_available, course, category);
        } catch (ProfessorNotFoundException e) {

            errorThrownOnUpdate = true;

            Report report = this.reportService.findReportById(id);
            List<Course> courses = this.courseService.findAllCourses();
            List<Category> categories = this.categoryService.findAllCategories();

            model.addAttribute("report", report);
            model.addAttribute("courses", courses);
            model.addAttribute("categories", categories);

            model.addAttribute("professorError",e.getMessage());

            model.addAttribute("setFalse",false);

            return "add-form";
        }
        if(!errorThrownOnUpdate){

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = "";
            if(principal instanceof UserDetails){
                username = ((UserDetails)principal).getUsername();
            }
            model.addAttribute("username", username);

            Report report = this.reportService.findReportById(id);
            List<Course> courses = this.courseService.findAllCourses();
            List<Category> categories = this.categoryService.findAllCategories();

            model.addAttribute("report", report);
            model.addAttribute("courses", courses);
            model.addAttribute("categories", categories);

            model.addAttribute("updateSuccessfull",true);

            model.addAttribute("setFalse",false);

            servletResponse.setHeader("Refresh", "2;url=/reports");
            return "add-form";
        }
        return "redirect:/reports";
    }
}
