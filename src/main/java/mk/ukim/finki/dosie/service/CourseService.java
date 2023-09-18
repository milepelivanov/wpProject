package mk.ukim.finki.dosie.service;

import mk.ukim.finki.dosie.model.Course;
import mk.ukim.finki.dosie.model.exceptions.CourseNotFoundException;

import java.util.List;

public interface CourseService {

    Course findCourseById(Long id) throws CourseNotFoundException;
    List<Course> findAllCourses();
}
