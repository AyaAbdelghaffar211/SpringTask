package com.example.demo.service;

import com.example.demo.dtos.CourseDTO;
import com.example.demo.dtos.mappers.CourseMapper;
import com.example.demo.model.Course;
import com.example.demo.recommender.ICourseRecommender;
import com.example.demo.repository.ICourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private ICourseRecommender courseRecommender;
    private ICourseRepository courseRepository;

    private final CourseMapper courseMapper = CourseMapper.INSTANCE;

    public CourseService(ICourseRecommender courseRecommender, ICourseRepository courseRepository) {
        this.courseRecommender = courseRecommender;
        this.courseRepository = courseRepository;
    }

    public List<Course> recommendCourses() {
        return courseRecommender.recommend();
    }

    public void addCourse(CourseDTO courseDTO){
        Course course = courseMapper.courseDTOToCourse(courseDTO);
        courseRepository.save(course);
    }

    public void updateCourseDescription(Long id, String description){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setDescription(description);
        courseRepository.save(course);
    }

    public CourseDTO findByID(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("course not found"));
        return courseMapper.courseToCourseDTO(course);
    }

    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }

    public Page<CourseDTO> getAllCourses(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursesPage = courseRepository.findAll(pageable);
        return coursesPage.map(courseMapper::courseToCourseDTO);
    }
}
