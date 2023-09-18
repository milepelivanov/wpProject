package mk.ukim.finki.dosie.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.dosie.model.*;
import mk.ukim.finki.dosie.model.enums.ReportType;

import mk.ukim.finki.dosie.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInit {
    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final ReportRepository reportRepository;
    private final PasswordEncoder passwordEncoder;
    public DataInit(CategoryRepository categoryRepository, CourseRepository courseRepository, ProfessorRepository professorRepository, StudentRepository studentRepository, ReportRepository reportRepository,
                    PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.reportRepository = reportRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init(){
        Category cat1 = categoryRepository.save(new Category("Dobra seminarska", ReportType.POSITIVE));
        Category cat2 = categoryRepository.save(new Category("Aktivnost predavanja", ReportType.POSITIVE));
        Category cat3 = categoryRepository.save(new Category("Prepisuvanje ispit", ReportType.NEGATIVE));
        Category cat4 = categoryRepository.save(new Category("Plagijat labaratoriska", ReportType.NEGATIVE));

        Student s1 = studentRepository.save(new Student("201045", "Jovan", "Manchev"));
        Student s2 = studentRepository.save(new Student("201057", "Mile", "Pelivanov"));
        Student s3 = studentRepository.save(new Student("201151", "Dimitar", "Marenoski"));

        professorRepository.save(new Professor("sasho.gramatikov", passwordEncoder.encode("123"), "Sasho", "Gramatikov"));
        professorRepository.save(new Professor("riste.stojanov", passwordEncoder.encode("123"), "Riste", "Stojanov"));
        professorRepository.save(new Professor("dimitar.trajanov", passwordEncoder.encode("123"), "Dimitar", "Trajanov"));
        professorRepository.save(new Professor("igor.mishkovski", passwordEncoder.encode("123"), "Igor", "Mishkovski"));
        professorRepository.save(new Professor("miroslav.mirchev", passwordEncoder.encode("123"), "Miroslav", "Mirchev"));

        Professor prof1 = professorRepository.findById("sasho.gramatikov").get();
        Professor prof2 = professorRepository.findById("riste.stojanov").get();
        Professor prof3 = professorRepository.findById("dimitar.trajanov").get();
        Professor prof4 = professorRepository.findById("igor.mishkovski").get();
        Professor prof5 = professorRepository.findById("miroslav.mirchev").get();

        List<Professor> course1Professors = new ArrayList<>();
        course1Professors.add(prof1);
        course1Professors.add(prof2);
        course1Professors.add(prof3);

        List<Professor> course2Professors = new ArrayList<>();
        course2Professors.add(prof4);
        course2Professors.add(prof3);

        List<Professor> course3Professors = new ArrayList<>();
        course3Professors.add(prof5);

        List<Professor> course4Professors = new ArrayList<>();
        course4Professors.add(prof1);
        course4Professors.add(prof2);
        course4Professors.add(prof3);

        Course c1 = courseRepository.save(new Course("Veb programiranje 2022/2023", course1Professors));
        Course c2 = courseRepository.save(new Course("Voved vo naukata za podatoci 2022/2023", course2Professors));
        Course c3 = courseRepository.save(new Course("Vovev vo mrezhna nauka 2022/2023", course3Professors));
        Course c4 = courseRepository.save(new Course("Operativni Sistemi 2022/2023", course4Professors));

        prof1.setCourses(new ArrayList<>(Arrays.asList(c1, c4)));
        prof2.setCourses(new ArrayList<>(Arrays.asList(c1, c4)));
        prof3.setCourses(new ArrayList<>(Arrays.asList(c1,c2, c4)));
        prof4.setCourses(new ArrayList<>(Arrays.asList(c2, c4)));
        prof5.setCourses(new ArrayList<>(Arrays.asList(c3)));

        prof1.setStudents(new ArrayList<>(Arrays.asList(s1,s2,s3)));
        prof2.setStudents(new ArrayList<>(Arrays.asList(s1,s2,s3)));
        prof3.setStudents(new ArrayList<>(Arrays.asList(s1,s2,s3)));
        prof4.setStudents(new ArrayList<>(Arrays.asList(s1,s2,s3)));
        prof5.setStudents(new ArrayList<>(Arrays.asList(s1,s2,s3)));

        professorRepository.save(prof1);
        professorRepository.save(prof2);
        professorRepository.save(prof3);
        professorRepository.save(prof4);
        professorRepository.save(prof5);

        Report r1 = new Report(5, "site poeni na testot za aktivnost", prof1, s1, c4, cat2);
        reportRepository.save(r1);

        Report r2 = new Report(5, "dobar proekt, za klasifikacija na kompanii vo gics subindustries", prof5, s1, c3, cat1);
        reportRepository.save(r2);

        Report r3 = new Report(5, "prepisuval operativni vo septemvriska", prof1, s2, c4, cat3);
        reportRepository.save(r3);


        Report r4 = new Report(5, "ista labaratoriska so drug kolega", prof1, s3, c4, cat4);
        reportRepository.save(r4);

        //od ovde
        Report r5 = new Report(5, "site poeni na testot za aktivnost", prof1, s1, c4, cat2);
        reportRepository.save(r5);

        Report r6 = new Report(5, "dobar proekt, za klasifikacija na kompanii vo gics subindustries", prof5, s1, c3, cat1);
        reportRepository.save(r6);

        Report r7 = new Report(5, "prepisuval operativni vo septemvriska", prof1, s2, c4, cat3);
        reportRepository.save(r7);


        Report r8 = new Report(5, "ista labaratoriska so drug kolega", prof1, s3, c4, cat4);
        reportRepository.save(r8);

        Report r9 = new Report(5, "site poeni na testot za aktivnost", prof1, s1, c4, cat2);
        reportRepository.save(r9);

        Report r10 = new Report(5, "dobar proekt, za klasifikacija na kompanii vo gics subindustries", prof5, s1, c3, cat1);
        reportRepository.save(r10);

        Report r11 = new Report(5, "prepisuval operativni vo septemvriska", prof1, s2, c4, cat3);
        reportRepository.save(r11);

        Report r12 = new Report(5, "site poeni na testot za aktivnost", prof1, s1, c4, cat2);
        reportRepository.save(r12);

        Report r13 = new Report(5, "dobar proekt, za klasifikacija na kompanii vo gics subindustries", prof5, s1, c3, cat1);
        reportRepository.save(r13);

        Report r14 = new Report(5, "prepisuval operativni vo septemvriska", prof1, s2, c4, cat3);
        reportRepository.save(r14);


        Report r15 = new Report(5, "ista labaratoriska so drug kolega", prof1, s3, c4, cat4);
        reportRepository.save(r15);


        Report r16 = new Report(5, "ista labaratoriska so drug kolega", prof1, s3, c4, cat4);
        reportRepository.save(r16);


    }
}
