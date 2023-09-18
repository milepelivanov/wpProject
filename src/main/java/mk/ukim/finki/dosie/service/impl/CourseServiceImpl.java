package mk.ukim.finki.dosie.service.impl;

import mk.ukim.finki.dosie.model.Course;
import mk.ukim.finki.dosie.model.exceptions.CourseNotFoundException;
import mk.ukim.finki.dosie.repository.CourseRepository;
import mk.ukim.finki.dosie.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course findCourseById(Long id) throws CourseNotFoundException{
        return this.courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course is not found"));
    }

    @Override
    public List<Course> findAllCourses() {
        return this.courseRepository.findAll();
    }
}
